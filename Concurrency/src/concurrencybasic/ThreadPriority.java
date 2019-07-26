import java.util.concurrent.TimeUnit;
import java.util.*;
public class ThreadPriority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws Exception {
        List<Job> jobs = new ArrayList<Job>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread t = new Thread(job, "Thread:" + i);
            t.setPriority(priority);
            t.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;
        for (Job job : jobs) {
            System.out.println("Job priority : " + job.priority+ ", Count : "+job.count);
        }

    }

    static class Job implements Runnable {
        private long count = 0;
        private int priority = 0;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                Thread.yield();
                count++;
            }
        }
    }
}
