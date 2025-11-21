import java.util.LinkedList;
import java.util.Queue;

public class Ride implements RideInterface {
    // 原有基础属性（保留）
    private String rideName;
    private String rideType;
    private Employee operator;

    // Part3新增：等待队列（用LinkedList实现Queue，FIFO）
    private Queue<Visitor> waitingLine = new LinkedList<>();

    // 原有构造函数（补充operator参数，适配后续演示）
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

    // ------------------------------ Part3：队列方法实现 ------------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingLine.offer(visitor); // Queue的FIFO添加
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
        Visitor removed = waitingLine.poll(); // Queue的FIFO移除
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

    // 其他接口方法（暂未实现，保留空实现）
    @Override public void addVisitorToHistory(Visitor visitor) {}
    @Override public boolean checkVisitorFromHistory(Visitor visitor) { return false; }
    @Override public int numberOfVisitors() { return 0; }
    @Override public void printRideHistory() {}
    @Override public void runOneCycle() {}
}
