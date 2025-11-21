public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();
        demo.partThree(); // 运行Part3演示
    }

    // ------------------------------ Part3：队列演示 ------------------------------
    public void partThree() {
        System.out.println("\n========== Part3：队列管理演示 ==========");
        // 创建操作员
        Employee operator = new Employee("张三", 30, "13800138000", "EMP001", "过山车操作");
        // 创建Ride对象
        Ride rollerCoaster = new Ride("过山车", "刺激类", operator);
        // 添加5个游客到队列（Part3要求：至少5个）
        rollerCoaster.addVisitorToQueue(new Visitor("Jack", 20, "12345", "VIS001", true));
        rollerCoaster.addVisitorToQueue(new Visitor("Sharon", 25, "67890", "VIS002", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Benny", 18, "11223", "VIS003", true));
        rollerCoaster.addVisitorToQueue(new Visitor("Leo", 30, "33445", "VIS004", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Lucy", 22, "55667", "VIS005", true));
        // 打印队列
        rollerCoaster.printQueue();
        // 移除1个游客
        rollerCoaster.removeVisitorFromQueue();
        // 打印移除后的队列
        rollerCoaster.printQueue();
    }

    // 其他演示方法（空实现，保留）
    public void partFourA() {}
    public void partFourB() {}
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}
