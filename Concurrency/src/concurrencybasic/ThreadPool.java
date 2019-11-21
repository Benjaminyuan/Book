package concurrencybasic;
public interface ThreadPool<Job extends Runnable>{
    public void excute(Job job);
    public void shutdown(Job job);
    public void addWorkers(int num);
    public void removeWorker(int num);
    public int getJobSize();
}