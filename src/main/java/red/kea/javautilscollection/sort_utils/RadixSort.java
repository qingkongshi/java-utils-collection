package red.kea.javautilscollection.sort_utils;

import java.util.ArrayList;

/**
 * @ClassName RadixSort
 * @Author KeA
 * @Date 2019/7/5 19:24
 * @Version 1.0
 */
public class RadixSort {
    /**
     * 基数排序是一种非比较型整数排序算法，其原理是将数据按位数切割成不同的数字，然后按每个位数分别比较。
     * 假设说，我们要对 100 万个手机号码进行排序，应该选择什么排序算法呢？
     * 排的快的有归并、快排时间复杂度是 O(nlogn)，计数排序和桶排序虽然更快一些，
     * 但是手机号码位数是11位，那得需要多少桶？内存条表示不服。
     *
     * 这个时候，我们使用基数排序是最好的选择。
     *
     * 基数排序可以看成桶排序的扩展，也是用桶来辅助排序
     */

    public static void sort(int[] arr){
        int length = arr.length;

        //最大值
        int max = arr[0];
        for(int i = 0;i < length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //当前排序位置
        int location = 1;

        //桶列表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();

        //长度为10 装入余数0-9的数据
        for(int i = 0; i < 10; i++){
            bucketList.add(new ArrayList());
        }

        while(true)
        {
            //判断是否排完
            int dd = (int)Math.pow(10,(location - 1));
            if(max < dd){
                break;
            }

            //数据入桶
            for(int i = 0; i < length; i++)
            {
                //计算余数 放入相应的桶
                int number = ((arr[i] / dd) % 10);
                bucketList.get(number).add(arr[i]);
            }

            //写回数组
            int nn = 0;
            for (int i=0;i<10;i++){
                int size = bucketList.get(i).size();
                for(int ii = 0;ii < size;ii ++){
                    arr[nn++] = bucketList.get(i).get(ii);
                }
                bucketList.get(i).clear();
            }
            location++;
        }
    }
    /**
     * 其实它的思想很简单，不管你的数字有多大，按照一位一位的排，0 - 9 最多也就十个桶：
     *
     * 先按权重小的位置排序，然后按权重大的位置排序。
     *
     * 当然，如果你有需求，也可以选择从高位往低位排。
     */
}
