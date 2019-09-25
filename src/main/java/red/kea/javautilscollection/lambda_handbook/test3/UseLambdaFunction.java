package red.kea.javautilscollection.lambda_handbook.test3;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @ClassName UseLambdaFunction
 * @Description 方法引用
 * @Author KeA
 * @Date 2019/9/25 10:04
 * @Version 1.0
 */
public class UseLambdaFunction {

    public static void main(String[] args) {
        Consumer<String> consumerStatic = MyFunction::testStatic;
        consumerStatic.accept("这是静态方法");

        MyFunction myFunction = new MyFunction();
        Consumer<String> consumerCommon = myFunction::testCommon;
        consumerCommon.accept("这是普通方法");

        Supplier<MyFunction> supplier = MyFunction::new;
        MyFunction myFunction1 = supplier.get();
        System.out.println(myFunction1);
    }

}
class MyFunction{
    public static void testStatic(String message){
        System.out.println(message);
    }
    public void testCommon(String message){
        System.out.println(message);
    }

    public MyFunction() {
    }
}
