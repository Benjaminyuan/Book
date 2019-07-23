package principle;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
public class SafeCounter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private volatile int i =0;
    public static void main(String[] args) {
        final SafeCounter safeCounter = new SafeCounter();
        List<Thread> threadList = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        safeCounter.safeCount();
                        safeCounter.count();
                    }
                }
            });
            threadList.add(t);
        }
        for (Thread t : threadList) {
            t.start();
        }
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis() -start;
        System.out.printf("i:%d\n",safeCounter.i);
        System.out.printf("atomic:%d\n",safeCounter.atomicI.get());
        System.out.printf("cost :%d",end);
    }
    private void safeCount(){
        for(;;){
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, i+1);
            if(suc){
                break;
            }
        }
    }
    private void count(){
        i++;
    }

}