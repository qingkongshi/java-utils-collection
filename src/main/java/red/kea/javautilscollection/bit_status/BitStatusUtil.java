package red.kea.javautilscollection.bit_status;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BitStatusUtil
 * @Description 位运算处理状态的工具类
 * @Author KeA
 * @Date 2019/9/25 10:54
 * @Version 1.0
 */
public class BitStatusUtil {
    //A:语文 B:数学 C:英语 D:物理 E:化学 F:生物 G:历史 H:地理

    // 二进制表示 0001 没有交任何作业
    public static final int NONE              = 1<<0;   //默认
    public static final int CHINESE           = NONE<<1;//语文
    public static final int MATH              = NONE<<2;//数学
    public static final int ENGLISH           = NONE<<3;//英语
    public static final int PHYSICS           = NONE<<4;//物理
    public static final int CHEMISTRY         = NONE<<5;//化学
    public static final int BIOLOGY           = NONE<<6;//生物
    public static final int HISTORY           = NONE<<7;//历史
    public static final int GEOGRAPHY         = NONE<<8;//地理

    public static final int ALL =NONE|CHINESE|MATH|ENGLISH|PHYSICS|CHEMISTRY|BIOLOGY|HISTORY|GEOGRAPHY;

    /**
     * @param status 所有状态值
     * @param value  需要判断状态值
     * @return 是否存在
     */
    public static boolean hasStatus(long status, long value) {
        return (status & value) != 0;
    }

    /**
     * @param status 已有状态值
     * @param value  需要添加状态值
     * @return 新的状态值
     */
    public static long addStatus(long status, long value) {
        if (hasStatus(status, value)) {
            return status;
        }
        return (status | value);
    }

    /**
     * @param status 已有状态值
     * @param value  需要删除状态值
     * @return 新的状态值
     */
    public static long removeStatus(long status, long value) {
        if (!hasStatus(status, value)) {
            return status;
        }
        return status ^ value;
    }

    /**是否交了含有全部状态
     * @param status
     * @return
     */
    public static boolean hasAllStatus(long status) {
        return (status & ALL) == ALL;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        long status = addStatus(NONE, CHINESE);
        System.out.println("小明交了语文作业:" + status);

        status = addStatus(status, PHYSICS);
        System.out.println("小明又交了物理作业:" + status);

        status = addStatus(status, HISTORY);
        System.out.println("小明还交了历史作业:" + status);

        status = removeStatus(status, HISTORY);
        System.out.println("小明撤销了历史作业:" + status);

        System.out.println("小明是否交了语文作业:" + hasStatus(status, CHINESE));
        System.out.println("小明是否交了历史作业:" + hasStatus(status, HISTORY));
        System.out.println("小明是否交了生物作业:" + hasStatus(status, BIOLOGY));
        System.out.println("小明是否交了全部作业:" + hasAllStatus(status));
    }
}
