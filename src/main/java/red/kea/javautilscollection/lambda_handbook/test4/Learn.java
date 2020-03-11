package red.kea.javautilscollection.lambda_handbook.test4;

/**
 * @InterfaceName Learn
 * @Description
 * @Author KeA
 * @Date 2020/3/11 9:27
 * @Version 1.0
 */
@FunctionalInterface
public interface Learn {
    void study();
}

@FunctionalInterface
interface LearnParam {
    void study(int a , int b);
}

@FunctionalInterface
interface LearnParamAndReturn {
    int study(int a , int b);
}