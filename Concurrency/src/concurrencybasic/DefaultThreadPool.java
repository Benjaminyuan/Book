package concurrencybasic;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import concurrencybasic.ThreadPool;
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{
    private static final int MAX_WORKER_NUM = 10;
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    private static final int MIN_WORKER_NUMBERS = 1;
    private final LinkedList<Job>  jobs = new LinkedList<Job>();
    private final List<Worker> workers =  Collections.synchronizedList(new ArrayList<Worker>());
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    private AtomicLong threadNum = new AtomicLong();
    public DefaultThreadPool(){
        initalizeWorkers(DEFAULT_WORKER_NUMBERS);
    }
    public DefaultThreadPool(int num){
        workerNum = num > MAX_WORKER_NUM ? MAX_WORKER_NUM : 
        num < MIN_WORKER_NUMBERS?MIN_WORKER_NUMBERS:num;
        initalizeWorkers(num);
    }
    public  void excute(Job job){
        if(iob != null){
            synchronized(jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }
    public void shutdown(){
        for(Worker worker : workers){
            worker.shutdown();
        }
    }
    public void addWorker(int num){
        synchronized(jobs){
            if(num + workerNum  > MAX_WORKER_NUM){
                num = MAX_WORKER_NUM - this.workerNum;
            }
            initalizeWorkers(num);
            this.workerNum += num;
        }
    }
    public void removeWorker(int num){
        synchronized(jobs){
            if(num < 0){
                throw new IllegalArgumentException("num can be nagative");
            }
            if(num >= this.workerNum){
                throw new IllegalArgumentException(" remove num is large than workerNum");
            }
            int count =0;
            while(count< num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }
    public int getJobSize(){
        return jobs.size();
    }
    public void initalizeWorkers(int num){
        for(int i=0;i<num;i++){
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-" +
            threadNum.incrementAndGet());
            thread.start();
        }
    }
    class Worker implements Runnable{
        private volatile boolean running = ture;
        public void run(){
            while(running){
                Job job = null;
                synchronized(jobs){
                   while(jobs.isEmpty()){
                        try{
                            jobs.wait();
                        }catch(InterruptedException e){
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                            return ;
                        }     
                   }
                   job = jobs.poll(); 
                }
                if(job != null ){
                    try{
                        job.run();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        public void shutdown(){
            running = false;
        }
    }
}
