import java.util.LinkedList;
import java.util.Iterator;
import java.util.Queue;

public class Ride implements RideInterface {
    // 原有属性（保留：基础属性+队列）
    private String rideName;
    private String rideType;
    private Employee operator;
    private Queue<Visitor> waitingLine = new LinkedList<>();

    // Part4A新增：骑乘历史（用LinkedList存储）
    private LinkedList<Visitor> rideHistory = new LinkedList<>();

    // 构造函数（保留，新增maxRider参数占位，为Part5准备）
    public Ride() {}
    public Ride(String rideName, String rideType, Employee operator) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
    }

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

    // 未实现的接口方法（保留空实现）
    @Override public void runOneCycle() {}
}
