package red.kea.javautilscollection.optimize;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName InlineVariable(内联变量)
 * @Author KeA
 * @Date 2019/9/6 17:23
 * @Version 1.0
 *
 * 内联临时变量将所有对该变量的引用动作，替换为对它赋值的那个表达式自身。
 *
 */
public class InlineVariable {
    //优化例子

    //内联临时变量之前
    private boolean getPrice1(){
        Map<String,Integer> map = new HashMap<>();
        map.put("price",1000);

        Integer basePrice = map.get("price");
        return basePrice > 888;
    }
    //内联临时变量之后
    private boolean getPrice2(){
        Map<String,Integer> map = new HashMap<>();
        map.put("price",1000);

        return map.get("price")> 888;
    }
}
