package concurrency;

import java.util.concurrent.TimeUnit;

public class VolatileTest {
    private  boolean stopThread = false;
    private MyThread myThread = new MyThread();
    public void setStopThread(boolean newValue){
        this.stopThread = newValue;
    }
    public void start(){
        stopThread = false;
        myThread.start();
    }
    public void stop(){
        stopThread  = true;
    }
    public static void main(String[] arg){
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        volatileTest.stop();
    }

    private class MyThread extends Thread{
        @Override
        public void run(){
            super.run();
            System.out.println(" thread start");
            while(!stopThread){
                System.out.println("still running");
            }
            System.out.println("thread stop");
        }
    }
}
