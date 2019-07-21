package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirtDataInThreadLocal {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    public static void main(String[] arg){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i=0; i<10; i++){
            MyThread myThread = new MyThread();
            executorService.execute(myThread);
            int a[] = {};
        }
    }
    private static class MyThread extends Thread{
        private static boolean flag = true;
        @Override
        public void run(){
            if (flag){
                threadLocal.set(this.getName()+".session info.");
                flag = false;
            }
            System.out.println(this.getName()+"线程"+threadLocal.get());

        }
    }
}
