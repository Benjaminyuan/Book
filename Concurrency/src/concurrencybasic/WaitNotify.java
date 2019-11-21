package concurrencybasic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    private static boolean flag = true;
    static Object lock = new Object();

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is ture wait@ "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread()+" flag is false" + 
                new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }

    }
    static class Notify implements Runnable{
        @Override
        public void run(){
            synchronized(lock){
                System.out.println(Thread.currentThread()+" hold lock notify @ "+
                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try{
                    TimeUnit.SECONDS.sleep(5);
                }catch(Exception e ){
                    e.printStackTrace();
                }
            }
            synchronized(lock){
                System.out.println(Thread.currentThread()+"hold lock again . sleep @" +
                 new SimpleDateFormat("HH:mm:ss").format(new Date())); 
            }
        }
    }
    public static void main(String[] arg) throws Exception{
        Thread waitThread = new Thread(new Wait(),"waitThread");
        waitThread.start();
        Thread notifyThread = new Thread(new Notify(),"NotifyThread");
        TimeUnit.SECONDS.sleep(1);
        notifyThread.start();

    }
}