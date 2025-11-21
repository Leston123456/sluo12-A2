import java.io.*;
import java.util.*;

public class Ride implements RideInterface {
    // 3个实例变量（名称、类型、操作员）
    private String rideName;
    private String rideType;
    private Employee operator;

    // Part3：等待队列（Queue用LinkedList实现FIFO）
    private Queue<Visitor> waitingLine = new LinkedList<>();

    // Part4A：骑乘历史（LinkedList）
    private LinkedList<Visitor> rideHistory = new LinkedList<>();

    // Part5：骑乘参数
    private int maxRider;
    private int numOfCycles = 0;

    // 默认构造函数
    public Ride() {}

    // 带参构造函数
    public Ride(String rideName, String rideType, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        this.maxRider = maxRider;
    }

    // Getter和Setter
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public String getRideType() { return rideType; }
    public void setRideType(String rideType) { this.rideType = rideType; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public int getNumOfCycles() { return numOfCycles; }

    // ------------------------------ Part3：队列管理 ------------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingLine.offer(visitor);
            System.out.println("成功添加游客 " + visitor.getName() + " 到 " + rideName + " 等待队列");
        } else {
            System.out.println("添加失败：游客信息为空");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println(rideName + " 等待队列为空，无法移除游客");
            return;
        }
        Visitor removed = waitingLine.poll();
        System.out.println("成功移除队列首个游客：" + removed.getName());
    }

    @Override
    public void printQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println(rideName + " 等待队列为空");
            return;
        }
        System.out.println(rideName + " 等待队列（按加入顺序）：");
        int index = 1;
        for (Visitor visitor : waitingLine) {
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------ Part4A：骑乘历史管理 ------------------------------
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println("成功添加游客 " + visitor.getName() + " 到 " + rideName + " 骑乘历史");
        } else {
            System.out.println("添加失败：游客信息为空");
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("检查失败：游客信息为空");
            return false;
        }
        boolean exists = rideHistory.contains(visitor);
        System.out.println("游客 " + visitor.getName() + " 是否在骑乘历史中：" + exists);
        return exists;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println(rideName + " 骑乘历史总人数：" + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println(rideName + " 骑乘历史为空");
            return;
        }
        System.out.println(rideName + " 骑乘历史：");
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------ Part4B：历史排序 ------------------------------
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (rideHistory.isEmpty()) {
            System.out.println(rideName + " 骑乘历史为空，无需排序");
            return;
        }
        Collections.sort(rideHistory, comparator);
        System.out.println(rideName + " 骑乘历史排序完成");
    }

    // ------------------------------ Part5：运行骑乘周期 ------------------------------
    @Override
    public void runOneCycle() {
        // 检查操作员
        if (operator == null) {
            System.out.println("运行失败：" + rideName + " 未分配操作员");
            return;
        }
        // 检查等待队列
        if (waitingLine.isEmpty()) {
            System.out.println("运行失败：" + rideName + " 无等待游客");
            return;
        }

        System.out.println("开始 " + rideName + " 第 " + (numOfCycles + 1) + " 轮运行（最多容纳 " + maxRider + " 人）");
        int count = 0;
        while (!waitingLine.isEmpty() && count < maxRider) {
            Visitor visitor = waitingLine.poll();
            addVisitorToHistory(visitor);
            count++;
        }
        numOfCycles++;
        System.out.println("完成 " + rideName + " 第 " + numOfCycles + " 轮运行，本次搭载 " + count + " 人");
    }

    // ------------------------------ Part6：导出历史到CSV ------------------------------
    public void exportRideHistory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Visitor visitor : rideHistory) {
                // CSV格式：姓名,年龄,联系方式,游客ID,是否会员
                String line = String.join(",",
                        visitor.getName(),
                        String.valueOf(visitor.getAge()),
                        visitor.getContact(),
                        visitor.getVisitorId(),
                        String.valueOf(visitor.isMember())
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("成功导出 " + rideName + " 骑乘历史到 " + filePath);
        } catch (IOException e) {
            System.out.println("导出失败：" + e.getMessage());
        }
    }

    // ------------------------------ Part7：从CSV导入历史 ------------------------------
    public void importRideHistory(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int importedCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.out.println("跳过无效行：" + line);
                    continue;
                }
                // 解析CSV数据创建Visitor对象
                Visitor visitor = new Visitor(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        parts[2],
                        parts[3],
                        Boolean.parseBoolean(parts[4])
                );
                rideHistory.add(visitor);
                importedCount++;
            }
            System.out.println("成功从 " + filePath + " 导入 " + importedCount + " 条游客记录");
        } catch (FileNotFoundException e) {
            System.out.println("导入失败：文件不存在 - " + e.getMessage());
        } catch (IOException | NumberFormatException e) {
            System.out.println("导入失败：" + e.getMessage());
        }
    }
}
