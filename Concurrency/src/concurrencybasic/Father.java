package concurrencybasic;



public class Father{
    public static int b =2;
    private static int a = 1;
    public void say(){
        System.out.println("i am your father");
    }
    public int getA(){
        return this.a ;
    }
    public static void main(String[] arg){
        Son s = new Son();
        Father f = s;
        f.say();
        System.out.println(f.b);
        System.out.println(s.b);
        System.out.println(f.getA());
        System.out.println(s.getA());
        s.say();
    }
}
class Son extends Father{
    private static int a = 2;
    public static int b =1;
    @Override
    public void say(){
        System.out.println("i am your son");
    }
    public int getA(){
        return this.a ;
    }
}