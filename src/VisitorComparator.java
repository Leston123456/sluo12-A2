import java.util.Comparator;

// Part4B要求：实现Comparator接口，至少2个属性比较
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 第一维度：年龄升序比较
        int ageCompare = Integer.compare(v1.getAge(), v2.getAge());
        if (ageCompare != 0) {
            return ageCompare;
        }
        // 第二维度：年龄相同时，会员在前（true > false）
        return Boolean.compare(v2.isMember(), v1.isMember());
    }
}
