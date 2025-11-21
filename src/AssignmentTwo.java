public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();
        demo.partThree();   // Part3
        demo.partFourA();   // Part4A
        demo.partFourB();   // Part4B
        demo.partFive();    // Part5
    }

    // Part3演示（更新构造函数：添加maxRider参数）
    public void partThree() {
        System.out.println("\n========== Part3：队列管理演示 ==========");
        Employee operator = new Employee("张三", 30, "13800138000", "EMP001", "过山车操作");
        Ride rollerCoaster = new Ride("过山车", "刺激类", operator, 2); // 新增maxRider=2
        rollerCoaster.addVisitorToQueue(new Visitor("Jack", 20, "12345", "VIS001", true));
        rollerCoaster.addVisitorToQueue(new Visitor("Sharon", 25, "67890", "VIS002", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Benny", 18, "11223", "VIS003", true));
        rollerCoaster.addVisitorToQueue(new Visitor("Leo", 30, "33445", "VIS004", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Lucy", 22, "55667", "VIS005", true));
        rollerCoaster.printQueue();
        rollerCoaster.removeVisitorFromQueue();
        rollerCoaster.printQueue();
    }

    // ------------------------------ Part4A：骑乘历史演示 ------------------------------
    public void partFourA() {
        System.out.println("\n========== Part4A：骑乘历史管理演示 ==========");
        // 创建操作员
        Employee operator = new Employee("李四", 28, "13900139000", "EMP002", "雷暴飞车操作");
        // 创建Ride对象
        Ride thunderstorm = new Ride("雷暴飞车", "极速类", operator, 4);// 新增maxRider=4
        // 添加5个游客到历史（Part4A要求：至少5个）
        Visitor v1 = new Visitor("Tom", 23, "77889", "VIS006", true);
        Visitor v2 = new Visitor("Sherly", 21, "99001", "VIS007", false);
        Visitor v3 = new Visitor("Ben", 27, "22334", "VIS008", true);
        Visitor v4 = new Visitor("David", 24, "44556", "VIS009", false);
        Visitor v5 = new Visitor("Lily", 26, "66778", "VIS010", true);
        thunderstorm.addVisitorToHistory(v1);
        thunderstorm.addVisitorToHistory(v2);
        thunderstorm.addVisitorToHistory(v3);
        thunderstorm.addVisitorToHistory(v4);
        thunderstorm.addVisitorToHistory(v5);
        // 检查游客是否在历史中
        thunderstorm.checkVisitorFromHistory(v3); // 存在
        thunderstorm.checkVisitorFromHistory(new Visitor("Bob", 30, "88990", "VIS011", false)); // 不存在
        // 打印历史人数
        thunderstorm.numberOfVisitors();
        // 打印历史详情（Iterator遍历）
        thunderstorm.printRideHistory();
    }

   // ------------------------------ Part4B：排序演示 ------------------------------
    public void partFourB() {
        System.out.println("\n========== Part4B：历史排序演示 ==========");
        // 创建操作员
        Employee operator = new Employee("王五", 32, "13700137000", "EMP003", "海盗船操作");
        // 创建Ride对象
        Ride pirateShip = new Ride("海盗船", "摇摆类", operator, 3);新增maxRider=3
        // 添加5个游客到历史（Part4B要求：至少5个）
        pirateShip.addVisitorToHistory(new Visitor("Alice", 19, "11122", "VIS012", false));
        pirateShip.addVisitorToHistory(new Visitor("Mike", 25, "22233", "VIS013", true));
        pirateShip.addVisitorToHistory(new Visitor("Emma", 19, "33344", "VIS014", true));
        pirateShip.addVisitorToHistory(new Visitor("Ryan", 28, "44455", "VIS015", false));
        pirateShip.addVisitorToHistory(new Visitor("Chloe", 25, "55566", "VIS016", true));
        // 排序前打印
        System.out.println("排序前：");
        pirateShip.printRideHistory();
        // 用自定义Comparator排序
        pirateShip.sortRideHistory(new VisitorComparator());
        // 排序后打印（验证结果）
        System.out.println("排序后（年龄升序，会员在前）：");
        pirateShip.printRideHistory();
    }

   // ------------------------------ Part5：周期运行演示 ------------------------------
    public void partFive() {
        System.out.println("\n========== Part5：骑乘周期运行演示 ==========");
        // 创建操作员
        Employee operator = new Employee("赵六", 35, "13600136000", "EMP004", "摩天轮操作");
        // 创建Ride对象（maxRider=6，单周期最多6人）
        Ride ferrisWheel = new Ride("摩天轮", "观光类", operator, 6);
        // 添加10个游客到队列（Part5要求：至少10个）
        for (int i = 1; i <= 10; i++) {
            ferrisWheel.addVisitorToQueue(new Visitor("游客" + i, 18 + i, "100" + i, "VIS0" + (16 + i), i % 2 == 0));
        }
        // 运行前打印队列
        System.out.println("运行前等待队列：");
        ferrisWheel.printQueue();
        // 运行1个周期
        ferrisWheel.runOneCycle();
        // 运行后打印队列和历史
        System.out.println("运行后等待队列：");
        ferrisWheel.printQueue();
        System.out.println("运行后骑乘历史：");
        ferrisWheel.printRideHistory();
    }

    // 其他演示方法（空实现，保留）
    public void partSix() {}
    public void partSeven() {}
}
