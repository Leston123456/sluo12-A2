public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();
        // 依次运行各部分演示
        demo.partThree();
        demo.partFourA();
        demo.partFourB();
        demo.partFive();
        demo.partSix();
        demo.partSeven();
    }

    public void partThree() {
        System.out.println("\n========== Part3：队列管理演示 ==========");
        Ride rollerCoaster = new Ride("过山车", "刺激类", new Employee("张三", 30, "13800138000", "EMP001", "过山车操作"), 2);
        // 添加5个游客
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

    public void partFourA() {
        System.out.println("\n========== Part4A：骑乘历史管理演示 ==========");
        Ride thunderstorm = new Ride("雷暴飞车", "极速类", new Employee("李四", 28, "13900139000", "EMP002", "雷暴飞车操作"), 4);
        // 添加5个游客到历史
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
        thunderstorm.checkVisitorFromHistory(v3);
        thunderstorm.checkVisitorFromHistory(new Visitor("Bob", 30, "88990", "VIS011", false));
        // 打印历史人数
        thunderstorm.numberOfVisitors();
        // 打印历史详情（用Iterator）
        thunderstorm.printRideHistory();
    }

    public void partFourB() {
        System.out.println("\n========== Part4B：历史排序演示 ==========");
        Ride pirateShip = new Ride("海盗船", "摇摆类", new Employee("王五", 32, "13700137000", "EMP003", "海盗船操作"), 3);
        // 添加5个游客
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
        // 排序后打印
        System.out.println("排序后（年龄升序，会员在前）：");
        pirateShip.printRideHistory();
    }

    public void partFive() {
        System.out.println("\n========== Part5：骑乘周期运行演示 ==========");
        Ride ferrisWheel = new Ride("摩天轮", "观光类", new Employee("赵六", 35, "13600136000", "EMP004", "摩天轮操作"), 6);
        // 添加10个游客到队列
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

    public void partSix() {
        System.out.println("\n========== Part6：导出历史到CSV演示 ==========");
        Ride logFlume = new Ride("激流勇进", "水上类", new Employee("孙七", 29, "13500135000", "EMP005", "激流勇进操作"), 4);
        // 添加5个游客
        logFlume.addVisitorToHistory(new Visitor("Zoe", 22, "66677", "VIS027", true));
        logFlume.addVisitorToHistory(new Visitor("Harry", 24, "77788", "VIS028", false));
        logFlume.addVisitorToHistory(new Visitor("Mia", 23, "88899", "VIS029", true));
        logFlume.addVisitorToHistory(new Visitor("Ethan", 26, "99900", "VIS030", false));
        logFlume.addVisitorToHistory(new Visitor("Olivia", 21, "00011", "VIS031", true));
        // 导出到CSV（文件路径可自定义）
        logFlume.exportRideHistory("ride_history.csv");
    }

    public void partSeven() {
        System.out.println("\n========== Part7：从CSV导入历史演示 ==========");
        Ride bumperCars = new Ride("碰碰车", "亲子类", new Employee("周八", 27, "13400134000", "EMP006", "碰碰车操作"), 4);
        // 从CSV导入
        bumperCars.importRideHistory("ride_history.csv");
        // 验证导入结果
        bumperCars.numberOfVisitors();
        bumperCars.printRideHistory();
    }
}
