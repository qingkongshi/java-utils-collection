package red.kea.javautilscollection.lambda_handbook.pool;

import red.kea.javautilscollection.lambda_handbook.usage.Usage;

/**
 * @ClassName TestFindAnyTask
 * @Author KeA
 * @Date 2019/8/22 15:34
 * @Version 1.0
 */
public class TestFindAnyTask implements Runnable {
    private int id;
    public TestFindAnyTask(int id){
        this.id = id;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 执行任务 " + id);
        Usage.testMergerList();
        System.out.println(Thread.currentThread().getName() + " 完成任务 " + id);
    }
}
