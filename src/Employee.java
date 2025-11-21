public class Employee extends Person {
    // 2个专属实例变量（Part1要求：员工ID、负责区域）
    private String employeeId;
    private String responsibilityArea;

    // 默认构造函数
    public Employee() {}

    // 带参构造函数（含父类属性）
    public Employee(String name, int age, String contact, String employeeId, String responsibilityArea) {
        super(name, age, contact);
        this.employeeId = employeeId;
        this.responsibilityArea = responsibilityArea;
    }

    // Getter和Setter
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getResponsibilityArea() { return responsibilityArea; }
    public void setResponsibilityArea(String responsibilityArea) { this.responsibilityArea = responsibilityArea; }
}
