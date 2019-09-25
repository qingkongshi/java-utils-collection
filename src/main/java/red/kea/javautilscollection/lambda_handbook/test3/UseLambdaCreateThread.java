package red.kea.javautilscollection.lambda_handbook.test3;

/**
 *
 * @ClassName UseLambdaCreateThread
 * @Description 函数式编程接口
 * @Author KeA
 * @Date 2019/9/25 9:50
 * @Version 1.0
 */
public class UseLambdaCreateThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("通常方式创建线程");
            }
        });

        Thread thread1 = new Thread(()-> System.out.println("使用Lambda表达式创建线程"));

        /**
         * 使用Lambda表达式，实际就是创建出该接口的实例对象。
         *
         * 使用Labmda表达式需要函数式编程接口，比如在Runnable接口上我们可以看到@FunctionalInterface注解（标记着这个接口只有一个抽象方法）
         *
         * 我们使用Lambda表达式创建线程的时候，并不关心接口名，方法名，参数名。我们只关注他的参数类型，参数个数，返回值。
         */
    }
}
