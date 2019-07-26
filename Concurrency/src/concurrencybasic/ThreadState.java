package concurrencybasic;

import java.util.concurrent.TimeUnit;

public class ThreadState {
    public static void main(String[] arg){
        new Thread(new TimeWaiting(),"timewaitingThread").start();
        new Thread(new Waiting(),"waiting").start();
        new Thread(new Blocked(),"Blocked-1").start();
        new Thread(new Blocked(),"Blocked-2").start();
    }
    static class TimeWaiting implements Runnable{
        @Override
        public void run(){
            while(true){
                try{
                    TimeUnit.SECONDS.sleep(100);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}