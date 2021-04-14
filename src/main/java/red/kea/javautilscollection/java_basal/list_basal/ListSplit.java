package red.kea.javautilscollection.java_basal.list_basal;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： KeA
 * @date： 2021-04-14 10:15:54
 * @version: 1.0
 * @describe: 拆分集合工具类
 */
public class ListSplit {

    /**
     * @方法描述：拆分大集合
     * @param list 大集合
     * @param size 每条数量
     * @return
     */
    public static List<List<T>> split(List<T> list, int size) {
        if (null == list || size == 0) {
            return null;
        }
        // 获得总数量
        int count = list.size();
        // 计算要拆分的个数
        int pageCount = (count / size) + (count % size == 0 ? 0 : 1);
        List<List<T>> temp = new ArrayList<>(pageCount);
        for (int i = 0, from = 0, to = 0; i < pageCount; i++) {
            from = i * size;
            to = from + size;
            to = to > count ? count : to;
            List<T> littleList = list.subList(from, to);
            temp.add(littleList);
        }
        return temp;
    }
}
