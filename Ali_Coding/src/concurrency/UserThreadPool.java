package concurrency;

import java.util.concurrent.*;

public class UserThreadPool {
    public static void main(String[] arg){
        BlockingQueue queue = new LinkedBlockingQueue(2);
        UserThreadFactory userThreadFactory_1 = new UserThreadFactory("factory-1");
        UserThreadFactory userThreadFactory_2 = new UserThreadFactory("factory-2");
        UserRejectHandler userRejectHandler = new UserRejectHandler();
        ThreadPoolExecutor threadPoolFirst = new ThreadPoolExecutor(2,4 ,
                60, TimeUnit.SECONDS ,queue ,userThreadFactory_1,userRejectHandler);
        ThreadPoolExecutor threadPoolSeconds = new ThreadPoolExecutor(1,5 ,
                60, TimeUnit.SECONDS ,queue ,userThreadFactory_2,userRejectHandler);
        Runnable task = new Task();
        Long start = System.nanoTime();
        for(int i = 0;i < 200; i++){
            threadPoolFirst.execute(task);
//            threadPoolSeconds.execute(task);
        }
        Long end  = System.nanoTime();
        System.out.println("timecost:"+ (end-start)+"ms");
    }

}