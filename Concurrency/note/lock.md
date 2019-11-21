## Lock接口
* 一些规范
  * 在finally中释放锁，不要将锁的释放过程写在try的代码块中，如果发生异常的话会导致锁无故释放
* Lock接口提供的关键字不具备的主要特征
  |特性|描述|
  |:-:|:-:|
  |尝试非阻塞地获取锁| 当前线程尝试获取锁，如果这一时刻没有其他线程竞争，则获取锁|
  |能被中断地获取锁| 与synchronized不同，获取到锁的线程可以响应中断，当前线程被中断时会抛出中断异常，同时锁会被释放|
  |超时获取锁| 在制定的截止时间获取锁，如果截止时间到了仍然无法获取锁，则返回|
## 队列同步器(AbstractQueuedSynchronizer)
* Java同步器框架提供的三个常用方法
  ```java
    int getState();
    void setState(int state);
    void compareAndSetState(int expect,int update);
  ```
* 实现函数
    ```java
    protected boolean tryAcquire(int arg)
    protected boolean tryRelease(int arg)
    protected int tryAcquireShared(int arg)
    protected boolean tryReleaseShared(int arg)
    protected boolean isHeldExclusively();
    ```
* 同步队列的实现
  * 同步队列
  * 独占式同步状态获取与释放
  * 共享同步状态获取和释放
  * 独占式超时获取同步状态