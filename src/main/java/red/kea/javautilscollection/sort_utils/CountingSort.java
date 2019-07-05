package red.kea.javautilscollection.sort_utils;

/**
 * @ClassName CountingSort
 * @Author KeA
 * @Date 2019/7/5 15:04
 * @Version 1.0
 */
public class CountingSort {
    /**
     * 计数排序是一种非基于比较的排序算法，
     * 我们之前介绍的各种排序算法几乎都是基于元素之间的比较来进行排序的，
     * 计数排序的时间复杂度为 O(n + m )，m 指的是数据量，
     * 说的简单点，计数排序算法的时间复杂度约等于 O(n)，快于任何比较型的排序算法。
     */

    public static void sort(int[] arr) {
        //找出数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //初始化计数数组
        int[] countArr = new int[max + 1];

        //计数
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;
            arr[i] = 0;
        }

        //排序
        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            if (countArr[i] > 0) {
                arr[index++] = i;
            }
        }
    }

    /**
     * 有一个需求就是当对成绩进行排名次的时候，
     * 如何在原来排前面的人，
     * 排序后还是处于相同成绩的人的前面。
     *
     * 解题的思路是对 countArr 计数数组进行一个变形，
     * 变来和名次挂钩，
     * 我们知道 countArr 存放的是分数的出现次数，
     * 那么其实我们可以算出每个分数的最大名次，
     * 就是将 countArr 中的每个元素顺序求和。
     *
     *
     * 变形之后是什么意思呢？
     *
     * 我们把原数组[ 2，5，8，2，5，4 ]中的数据依次拿来去 countArr 去找，
     * 你会发现 3 这个数在 countArr[3] 中的值是 2 ，代表着排名第二名，（因为第一名是最小的 2，对吧？），
     * 5 这个数在 countArr[5] 中的值是 5 ，为什么是 5 呢？我们来数数，排序后的数组应该是[ 2，3，4，5，5，8 ]，
     * 5 的排名是第五名，那 4 的排名是第几名呢？对应 countArr[4] 的值是 3 ，第三名，
     * 5 的排名是第五名是因为 5 这个数有两个，自然占据了第 4 名和第 5 名。
     *
     * 所以我们取排名的时候应该特别注意，原数组中的数据要从右往左取，
     * 从 countArr 取出排名后要把 countArr 中的排名减 1 ，
     * 以便于再次取重复数据的时候排名往前一位。
     */

    public static void sort2(int[] arr) {
        //找出数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //初始化计数数组
        int[] countArr = new int[max + 1];

        //计数
        for (int i = 0; i < arr.length; ++i) {
            countArr[arr[i]]++;
        }

        //顺序累加
        for (int i = 1; i < max + 1; ++i) {
            countArr[i] = countArr[i-1] + countArr[i];
        }

        //排序后的数组
        int[] sortedArr = new int[arr.length];

        //排序
        for (int i = arr.length - 1; i >= 0; --i) {
            sortedArr[countArr[arr[i]]-1] = arr[i];
            countArr[arr[i]]--;
        }

        //将排序后的数据拷贝到原数组
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = sortedArr[i];
        }
    }

    /**
     * 计数局限性
     * 计数排序的毛病很多，我们来找找 bug 。
     *
     * 如果我要排的数据里有 0 呢？int[] 初始化内容全是 0 ，排毛线。
     *
     * 如果我要排的数据范围比较大呢？比如[ 1，9999 ]，我排两个数你要创建一个 int[10000] 的数组来计数？
     *
     * 对于第一个 bug ，我们可以使用偏移量来解决，比如我要排[ -1，0，-3 ]这组数字，
     * 这个简单，我全给你们加 10 来计数，变成[ 9，10，7 ]计完数后写回原数组时再减 10。
     * 不过有可能也会踩到坑，万一你数组里恰好有一个 -10，你加上 10 后又变 0 了，排毛线。
     *
     * 对于第二个 bug ，确实解决不了，如果是[ 9998，9999 ]这种虽然值大但是相差范围不大的数据我们也可以使用偏移量解决，
     * 比如这两个数据，我减掉 9997 后只需要申请一个 int[3] 的数组就可以进行计数。
     *
     * 由此可见，计数排序只适用于正整数并且取值范围相差不大的数组排序使用，它的排序的速度是非常可观的。
     */
}
