package red.kea.javautilscollection.lambda_handbook.test1.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestFindAnyThreadPoolExecutor
 * @Author KeA
 * @Date 2019/8/22 15:32
 * @Version 1.0
 */
public class TestFindAnyThreadPoolExecutor {
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            5, //核心线程数
            20, //最大线程数
            30L,//线程空闲时间
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(5)
    );

    private TestFindAnyThreadPoolExecutor(){
    }

    public static ThreadPoolExecutor getThreadPoolExecutor(){
        return threadPoolExecutor;
    }

    public void execute(Runnable task) {
        threadPoolExecutor.execute(task);
    }
}
