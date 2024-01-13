import inf.TestImpl;
import inf.TestInf;

public class Main {
    public static void main(String [] args){
        TestInf testInf = new TestImpl();
        var test = testInf.test();
        System.out.printf("test=%s",test);
    }
}
