# PROG2004 A2: sluo12-A2 (Theme Park Management System)
## 项目结构
src/
├── AssignmentTwo.java  # 主类，包含partThree()~partSeven()演示方法
├── Person.java         # 抽象父类，含姓名、年龄、联系方式
├── Employee.java       # 子类（继承Person），管理员工ID、负责区域
├── Visitor.java        # 子类（继承Person），管理游客ID、会员状态
├── Ride.java           # 核心类（实现RideInterface），处理队列/历史/运行周期
├── RideInterface.java  # 接口，定义队列/历史/运行相关方法
└── VisitorComparator.java  # 比较器，实现Visitor多变量排序
## 开发进度
- Week1：完成Part1（类设计）、Part2（接口/抽象类）、Part3（Queue队列）
## 核心模块功能说明
### Part3：Queue队列管理
- 功能：用LinkedList实现Queue，支持游客FIFO排队（add/remove/print）；
- 关键逻辑：`addVisitorToQueue`校验游客非空，`printQueue`按加入顺序打印详情。

### Part5：骑乘周期运行（runOneCycle）
- 触发条件：需分配操作员（Employee非空）+ 等待队列非空；
- 数据流向：从Queue取`maxRider`个游客 → 加入LinkedList历史 → 更新`numOfCycles`。

## 开发进度跟踪
- 所有功能已通过`AssignmentTwo.java`的`partThree()`~`partSeven()`方法演示，运行无报错；
- GitHub提交记录按“Week1-Part1→Week3-Part7”顺序排列，可追溯完整开发流程。
