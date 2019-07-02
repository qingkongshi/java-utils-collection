package red.kea.javautilscollection.sort_utils;

/**
 * @ClassName InsertionSort
 * @Author KeA
 * @Date 2019/7/1 17:34
 * @Version 1.0
 * @Desribe 插入排序:插入排序的思想和我们打扑克摸牌的时候一样，从牌堆里一张一张摸起来的牌都是乱序的，我们会把摸起来的牌插入到左手中
 * 合适的位置，让左手中的牌时刻保持一个有序的状态。
 *
 * 那如果我们不是从牌堆里摸牌，而是左手里面初始化就是一堆乱牌呢？一样的道理，我们把牌往手的右边挪一挪，把手的左边空出一点位置来，然后
 * 在乱牌中抽一张出来，插入到左边，再抽一张出来，插入到左边，再抽一张，插入到左边，每次插入都插入到左边合适的位置，时刻保持左边的牌是
 * 有序的，直到右边的牌抽完，则排序完毕。
 *
 * @Result 从代码里我们可以看出，如果找到了合适的位置，就不会再进行比较了，就好比牌堆里抽出的一张牌本身就比我手里的牌都小，那么我只
 * 需要直接放在末尾就行了，不用一个一个去移动数据腾出位置插入到中间。
 *
 * 所以说，最好情况的时间复杂度是 O(n)，最坏情况的时间复杂度是 O(n2)，然而时间复杂度这个指标看的是最坏的情况，而不是最好的情况，所以
 * 插入排序的时间复杂度是 O(n2)。
 */
public class InsertionSort {
    /**
     * 插入排序(InsertionSort)
     * @param arr
     * @return
     */
    public static int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int value = arr[i];
            //插入的位置
            int j = 0;
            for (j = i-1; j >= 0; j--) {
                if (arr[j] > value) {
                    //移动数据
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            //插入数据
            arr[j+1] = value;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] arr = {8,5,3,4,2};
        sort(arr);
    }
}
