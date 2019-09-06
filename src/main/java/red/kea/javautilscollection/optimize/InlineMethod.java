package red.kea.javautilscollection.optimize;

import red.kea.javautilscollection.lambda_handbook.test1.bean.Student;

/**
 * @ClassName InlineMethod(内联函数)
 * @Author KeA
 * @Date 2019/9/6 16:26
 * @Version 1.0
 *
 * 内联函数就是在函数调用点插入函数本体，然后移除该函数。
 *
 * 提炼函数介绍了提炼函数代码优化方式，以简短清晰的小函数为荣。
 * 但是呢，小函数是不是越多越好呢？
 * 肯定不是啦，有时候你会遇到某些函数，其内部代码和函数名称同样清晰，这时候呢你可以考虑内联函数优化一下了。
 */
public class InlineMethod {
    //优化案例

    private Integer numberOfLateDeliveries = 3;

    //内联函数之前
    private int getRating(){
        return moreThanFiveDeliveries() ? 2 : 1;
    }
    private boolean moreThanFiveDeliveries(){
        return numberOfLateDeliveries >5;
    }

    //内联函数之后
    private int getRating1(){
        return numberOfLateDeliveries >5 ? 2 : 1;
    }

}
