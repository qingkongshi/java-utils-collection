package red.kea.javautilscollection.java_basal.map_basal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName MapTraversal
 * @Author KeA
 * @Date 2019/8/28 10:40
 * @Version 1.0
 */
public class MapTraversal {
    // 循环遍历Map的4中方法

    /**
     * 备注
     *
     * 如果只是获取key，或者value，推荐使用keySet或者values方式；
     *
     * 如果同时需要key和value推荐使用entrySet；
     *
     * 如果需要在遍历过程中删除元素推荐使用Iterator；
     *
     * 如果需要在遍历过程中增加元素，可以新建一个临时map存放新增的元素，等遍历完毕，再把临时map放到原来的map中。
     */
    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();
        map.put("a","m");
        map.put("b","n");

        // 1. entrySet遍历，在键和值都需要时使用（最常用）
        System.out.println("1. entrySet遍历，在键和值都需要时使用（最常用）");
        for(Map.Entry<String ,String> entry :map.entrySet()){
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
        }

        // 2. 通过keySet或values来实现遍历,性能略低于第一种方式
        // 遍历map中的键
        System.out.println("2. 通过keySet或values来实现遍历,性能略低于第一种方式");
        for (String key : map.keySet()){
            System.out.println("key:"+key);
        }
        for (String value : map.values()){
            System.out.println("value:"+value);
        }

        // 3. 使用Iterator遍历
        System.out.println("3. 使用Iterator遍历");
        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
        }

        // 4. java8 Lambda
        // java8提供了Lambda表达式支持，语法看起来更简洁，可以同时拿到key和value，
        // 不过，经测试，性能低于entrySet,所以更推荐用entrySet的方式
        System.out.println("4. java8 Lambda");
        map.forEach((key,value)->{
            System.out.println("key:"+key+",value:"+value);
        });

    }
}
