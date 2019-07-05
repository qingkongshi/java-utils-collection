package red.kea.javautilscollection.sort_utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName BucketSort
 * @Author KeA
 * @Date 2019/7/5 19:14
 * @Version 1.0
 */
public class BucketSort {
    /**
     * 桶排序可以看成是计数排序的升级版，
     * 它将要排的数据分到多个有序的桶里，
     * 每个桶里的数据再单独排序，
     * 再把每个桶的数据依次取出，即可完成排序。
     *
     * 这个桶排序乍一看好像挺简单的，但是要敲代码就需要考虑几个问题了。
     *
     * 桶这个东西怎么表示？
     *
     * 怎么确定桶的数量？
     *
     * 桶内排序用什么方法排？
     */

    public static void sort(int[] arr){
        //最大最小值
        int max = arr[0];
        int min = arr[0];
        int length = arr.length;

        for(int i=1; i<length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            } else if(arr[i] < min) {
                min = arr[i];
            }
        }

        //最大值和最小值的差
        int diff = max - min;

        //桶列表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for(int i = 0; i < length; i++){
            bucketList.add(new ArrayList<>());
        }

        //每个桶的存数区间
        float section = (float) diff / (float) (length - 1);

        //数据入桶
        for(int i = 0; i < length; i++){
            //当前数除以区间得出存放桶的位置 减1后得出桶的下标
            int num = (int) (arr[i] / section) - 1;
            if(num < 0){
                num = 0;
            }
            bucketList.get(num).add(arr[i]);
        }

        //桶内排序
        for(int i = 0; i < bucketList.size(); i++){
            //jdk的排序速度当然信得过
            Collections.sort(bucketList.get(i));
        }

        //写入原数组
        int index = 0;
        for(ArrayList<Integer> arrayList : bucketList){
            for(int value : arrayList){
                arr[index] = value;
                index++;
            }
        }
    }
    /**
     * 桶当然是一个可以存放数据的集合，我这里使用 arrayList ，如果你使用 LinkedList 那其实也是没有问题的。
     *
     * 桶的数量我认为设置为原数组的长度是合理的，因为理想情况下每个数据装一个桶。
     *
     * 数据入桶的映射算法其实是一个开放性问题，我承认我这里写的方案并不佳，因为我测试过不同的数据集合来排序，如果你有什么更好的方案或想法，欢迎留言讨论。
     *
     * 桶内排序为了方便起见使用了当前语言提供的排序方法，如果对于稳定排序有所要求，可以选择使用自定义的排序算法。
     */

    /**
     * 在额外空间充足的情况下，尽量增大桶的数量，极限情况下每个桶只有一个数据时，或者是每只桶只装一个值时，完全避开了桶内排序的操作，桶排序的最好时间复杂度就能够达到 O(n)。
     *
     * 比如高考总分 750 分，全国几百万人，我们只需要创建 751 个桶，循环一遍挨个扔进去，排序速度是毫秒级。
     *
     * 但是如果数据经过桶的划分之后，桶与桶的数据分布极不均匀，有些数据非常多，有些数据非常少，比如[ 8，2，9，10，1，23，53，22，12，9000 ]这十个数据，
     *
     * 我们分成十个桶装，结果发现第一个桶装了 9 个数据，这是非常影响效率的情况，会使时间复杂度下降到 O(nlogn)，
     *
     * 解决办法是我们每次桶内排序时判断一下数据量，
     *
     * 如果桶里的数据量过大，那么应该在桶里面回调自身再进行一次桶排序。
     */
}
