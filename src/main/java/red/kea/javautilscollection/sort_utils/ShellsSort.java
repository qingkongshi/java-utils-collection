package red.kea.javautilscollection.sort_utils;

/**
 * @ClassName ShellsSort
 * @Author KeA
 * @Date 2019/7/3 17:52
 * @Version 1.0
 * @Describe 希尔排序：也称作“缩小增量排序”，是插入排序的一种更高效的改进版本。
 *
 * 我们知道，插入排序对于大规模的乱序数组的时候效率是比较慢的，因为它每次只能将数据移动一位，希尔排序为了加快插入的速度，让数据移动的时候可以实现跳跃移动，节省了一部分的时间开支。
 *
 * @Result 可能你会问为什么区间要以 gap = gap*3 + 1 去计算，其实最优的区间计算方法是没有答案的，这是一个长期未解决的问题，不过差不多都会取在二分之一到三分之一附近。
 */
public class ShellsSort {

    public static int[] sort(int[] arr) {
        int length = arr.length;
        //区间
        int gap = 1;
        while (gap < length) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                //跨区间排序
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = gap / 3;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] arr = {8,5,3,4,2};
        sort(arr);
    }
}
