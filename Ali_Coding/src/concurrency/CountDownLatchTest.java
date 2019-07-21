package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    public static void main(String[] arg) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        /*----------------------使用信号纬度的同步方式----------------------*/
        for(int i=0; i<10;i++){
            new SecurityCheckThread(i, semaphore).start();
        }
        /*----------------------使用信号纬度的同步方式----------------------*/
    
        /*------------------------时间同步----------------*/
        for(int i =0;i<10;i++){
            new CountDown(Integer.toString(i), countDownLatch);
        }
        countDownLatch.wait();
        /*------------------------时间同步----------------*/

    }

    private static class SecurityCheckThread extends Thread {
        private int seq;
        private Semaphore semaphore;
        public  SecurityCheckThread(int seq, Semaphore semaphore){
            this.semaphore = semaphore;
            this.seq = seq;
        }
        @Override
        public void run(){
            try{
                semaphore.acquire();
                System.out.println("No."+seq+"乘客，正在检查");
                if (seq %2 == 0){
                    System.out.println("No."+seq+"乘客，身份可疑，不能出国" );
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }finally{
                semaphore.release();
                System.out.println("No."+seq+ "乘客已完成服务");
                if(this.seq == 9){
                    System.out.println("-----------");
                }
            }
        }

    }
    private static class CountDown extends Thread {
        private  final CountDownLatch count;
        private String content;
        public CountDown(String content, CountDownLatch countdown){
            this.content = content;
            this.count = countdown;
        }
     @Override
        public void run(){
            if(Math.random() >=0.5){
                throw new RuntimeException("原文存在非法字符");
            }
            System.out.println(content + "的翻译已经完成，译文");
            count.countDown();
     }
    }
}
