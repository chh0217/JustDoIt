package ch;

public class Binary {

    private static final int a = 1;//0001
    private static final int b = 2;//0010
    private static final int c = 4;//0100
    private static final int d = 8;//1000

    public static void main(String[] args) {
        int x = a | b;
        System.out.println(x);
        System.out.println((x & a) == a);
        System.out.println((x & b) == b);
        System.out.println((x & c) == c);

        int y = x | c;
        System.out.println(y);
        System.out.println((y & a) == a);
        System.out.println((y & b) == b);
        System.out.println(y & b);
        System.out.println((y & c) == c);

    }

}
