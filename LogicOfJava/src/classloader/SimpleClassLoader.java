package classloader;
public class SimpleClassLoader{
    public static void main(String[] args) {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        ClassLoader simpleLoader = simpleClassLoader.getClass().getClassLoader();
        while(simpleLoader != null ){
            System.out.println(simpleClassLoader.getClass().getName());
            simpleLoader = simpleLoader.getParent();
        }
        System.out.println(String.class.getClassLoader());//BootStrapLoader 非Java实现 为null
    }
}