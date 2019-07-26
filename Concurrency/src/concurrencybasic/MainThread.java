// package  concurrencybasic;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MainThread{
    public static void main(String[] arg) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for(ThreadInfo threadInfo : threadInfos){
            System.out.println("["+threadInfo.getThreadId()+"] "+threadInfo.getThreadName());

        }
    }
}