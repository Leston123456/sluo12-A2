public class Visitor extends Person {
    // 2个专属实例变量（Part1要求：游客ID、是否会员）
    private String visitorId;
    private boolean isMember;

    // 默认构造函数
    public Visitor() {}

    // 带参构造函数（含父类属性）
    public Visitor(String name, int age, String contact, String visitorId, boolean isMember) {
        super(name, age, contact);
        this.visitorId = visitorId;
        this.isMember = isMember;
    }

    // Getter和Setter
    public String getVisitorId() { return visitorId; }
    public void setVisitorId(String visitorId) { this.visitorId = visitorId; }
    public boolean isMember() { return isMember; }
    public void setMember(boolean member) { isMember = member; }

    // 重写toString（便于后续打印）
    @Override
    public String toString() {
        return "Visitor{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", contact='" + getContact() + '\'' +
                ", visitorId='" + visitorId + '\'' +
                ", isMember=" + isMember +
                '}';
    }
}
