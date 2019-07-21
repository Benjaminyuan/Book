package basictest;
import java.util.LinkedList;
import java.util.Queue;

public class StaticTest extends father {
    {
        System.out.println("son_1：构造块");
    }
    static{
        System.out.println("son_2: 静态块");
    }
    public StaticTest(){
        //默认为public
        System.out.println("son:构造函数");
    }
    public static void main(String[] args){
        StaticTest staticTest = new StaticTest();
        Queue<Integer> queue = new LinkedList<Integer>();
        /*
        father_2: 静态块
         son_2: 静态块
        father_1：构造块
        father:构造函数
        son_1：构造块
        son:构造函数
        */
    }
}
class father {
    {
        System.out.println("father_1：构造块");
    }
    static{
        System.out.println("father_2: 静态块");
    }
    public father(){
        //默认为public
        System.out.println("father:构造函数");
    }
}