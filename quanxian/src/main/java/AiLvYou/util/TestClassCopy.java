package AiLvYou.util;

public class TestClassCopy {
    int a;
    int b;

    public static void main(String[] args) {
        TestClassCopy aa = new TestClassCopy();
        aa.a = 1;
        aa.b = 1;

        TestClassCopy bb = new TestClassCopy();
        bb = aa;
        bb.b = 2;
        System.out.println(aa.b);
    }
}
