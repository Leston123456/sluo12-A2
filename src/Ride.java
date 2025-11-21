import java.util.LinkedList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Collections;

public class Ride implements RideInterface {
    // 原有属性（保留：基础属性+队列）
    private String rideName;
    private String rideType;
    private Employee operator;
    private Queue<Visitor> waitingLine = new LinkedList<>();
    private LinkedList<Visitor> rideHistory = new LinkedList<>();

    // Part5新增：骑乘周期属性
    private int maxRider;       // 单周期最大游客数（Part5要求：至少1人）
    private int numOfCycles = 0;// 周期计数（默认0，每次运行+1）
    
    // 构造函数（新增maxRider参数，适配演示）
    public Ride() {}
    public Ride(String rideName, String rideType, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        this.maxRider = maxRider;
    }
    // 新增Getter/Setter（Part5属性）
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public int getNumOfCycles() { return numOfCycles; }


    // 原有Getter/Setter（保留）
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public String getRideType() { return rideType; }
    public void setRideType(String rideType) { this.rideType = rideType; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }

    // ------------------------------ Part3：队列方法（保留，无修改） ------------------------------
    @Override public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) { waitingLine.offer(visitor); System.out.println("成功添加游客 " + visitor.getName() + " 到 " + rideName + " 等待队列"); }
        else { System.out.println("添加失败：游客信息为空"); }
    }
    @Override public void removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) { System.out.println(rideName + " 等待队列为空，无法移除游客"); return; }
        Visitor removed = waitingLine.poll(); System.out.println("成功移除队列首个游客：" + removed.getName());
    }
    @Override public void printQueue() {
        if (waitingLine.isEmpty()) { System.out.println(rideName + " 等待队列为空"); return; }
        System.out.println(rideName + " 等待队列（按加入顺序）：");
        int index = 1; for (Visitor visitor : waitingLine) { System.out.println(index + ". " + visitor); index++; }
    }

    // ------------------------------ Part4A：骑乘历史方法实现 ------------------------------
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
        // Part4A要求：必须用Iterator遍历（否则无分）
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------ Part4B：排序方法实现 ------------------------------
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (rideHistory.isEmpty()) {
            System.out.println(rideName + " 骑乘历史为空，无需排序");
            return;
        }
        // Part4B要求：用Collections.sort+Comparator排序（不能用Comparable）
        Collections.sort(rideHistory, comparator);
        System.out.println(rideName + " 骑乘历史排序完成");
    }

    // ------------------------------ Part5：运行周期方法实现 ------------------------------
    @Override
    public void runOneCycle() {
        // 校验1：是否有操作员（Part5要求）
        if (operator == null) {
            System.out.println("运行失败：" + rideName + " 未分配操作员");
            return;
        }
        // 校验2：是否有等待游客（Part5要求）
        if (waitingLine.isEmpty()) {
            System.out.println("运行失败：" + rideName + " 无等待游客");
            return;
        }
        // 开始运行周期
        System.out.println("开始 " + rideName + " 第 " + (numOfCycles + 1) + " 轮运行（最多容纳 " + maxRider + " 人）");
        int count = 0;
        // 转移游客：从队列到历史（不超过maxRider）
        while (!waitingLine.isEmpty() && count < maxRider) {
            Visitor visitor = waitingLine.poll(); // 移除队列首个游客
            addVisitorToHistory(visitor);         // 添加到历史
            count++;
        }
        // 周期计数+1
        numOfCycles++;
        System.out.println("完成 " + rideName + " 第 " + numOfCycles + " 轮运行，本次搭载 " + count + " 人");
    }
}
