package red.kea.javautilscollection.multithreading;

import java.util.concurrent.*;

/**
 * @author： KeA
 * @date： 2022-01-08 14:49:41
 * @version: 1.0
 * @describe:
 *
 * 定义以一个核心，最大线程数都是2，阻塞队列最大3的线程池
 *
 * 拒绝策略定为AbortPolicy时，只能执行5条任务，第6条抛出异常
 *
 * 拒绝策略定为CallerRunsPolicy时，能全部执行。
 *（csdn上看的解释，不太明白）
 * 当提交到第6个任务的时候，会触发拒绝策略，在这里我们配置了CallerRunsPolicy策略，
 * 主线程直接执行第六个任务去了，不再向下执行main方法中的es.execute(t7)这段代码。
 * 也就是说，在本程序中最多会有3个任务在执行，3个在等待。由此限制了线程池的等待任务数与执行线程数。
 * 所以JDK文档才会说：“这提供了一个简单的反馈控制机制，将降低新任务提交的速度”。
 */
public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0l, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        MyTask m1 = new MyTask("id=1");
        MyTask m2 = new MyTask("id=2");
        MyTask m3 = new MyTask("id=3");
        MyTask m4 = new MyTask("id=4");
        MyTask m5 = new MyTask("id=5");
        MyTask m6 = new MyTask("id=6");
        MyTask m7 = new MyTask("id=7");
        MyTask m8 = new MyTask("id=8");

        es.execute(m1);
        es.execute(m2);
        es.execute(m3);
        es.execute(m4);
        es.execute(m5);
        es.execute(m6);
        es.execute(m7);
        es.execute(m8);
    }
}
class MyTask implements Runnable{

    private String id;

    public MyTask(String id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(id);
    }
}
