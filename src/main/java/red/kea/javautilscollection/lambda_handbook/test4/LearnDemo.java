package red.kea.javautilscollection.lambda_handbook.test4;

/**
 * @ClassName LearnDemo
 * @Description
 * @Author KeA
 * @Date 2020/3/11 9:28
 * @Version 1.0
 */
public class LearnDemo {
    public static void main(String[] args) {
        Learn learn = ()-> System.out.println("学习");
        learn.study();


        LearnParam learnParam = (a,b)->System.out.println(a+"+"+b+"="+(a+b));
        learnParam.study(4,2);

        LearnParamAndReturn learnParamAndReturn = (a,b)-> a+b;
        int study = learnParamAndReturn.study(2, 3);
        System.out.println(study);
    }
}
