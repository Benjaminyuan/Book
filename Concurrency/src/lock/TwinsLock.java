package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {
        public Sync(int arg) {
            if (arg <= 0) {
                throw new IllegalAccessError("count must large than zero");
            }
            setState(arg);
        }

        protected int tryAcquireShared(int arg) {
            for (;;) {
                int current = getState();
                int newCount = current - arg;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        protected boolean tryReleaseShared(int returnCount) {
            for (;;) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryReleaseShared(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}