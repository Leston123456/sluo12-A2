public abstract class Person {
    // 3个实例变量（姓名、年龄、联系方式）
    private String name;
    private int age;
    private String contact;

    // 默认构造函数
    public Person() {}

    // 带参构造函数
    public Person(String name, int age, String contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    // Getter和Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
