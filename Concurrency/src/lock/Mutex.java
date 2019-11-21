package lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.*;
public class Mutex implements Lock {
    private static class Sync extends AbstractQueuedSynchronizer{
        
        protected boolean isHeldExclusively(){
            return getState() == 1;
        }
        @Override
        protected boolean tryAcquire(int acquires){
            if(compareAndSetState(0, 1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        @Override 
        protected boolean tryRelease(int releases){
            if(getState()== 0){
                throw new IllegalMonitorStateException();
            }
            //独占锁，锁的释放不需要加锁，因为只有一个线程
            setExclusiveOwnerThread(Thread.currentThread());
            setState(0);
            return true;
        }
        public Condition newCondition(){
            return new ConditionObject();
        }
    } 
    private final Sync sync = new Sync();
    @Override
    public void lock(){
        sync.acquire(1);
    }
    @Override
    public boolean tryLock(){
        return sync.tryAcquire(1);
    }
    @Override
    public void unlock(){
        sync.release(1);
    }
    @Override
    public Condition newCondition(){
        return sync.newCondition();
    }
    @Override
    public void lockInterruptibly() throws InterruptedException{
        sync.acquireInterruptibly(1);
    }
    @Override
    public boolean tryLock(Long timeout,TimeUnit unit) throws InterruptedException{
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
    
    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }

}