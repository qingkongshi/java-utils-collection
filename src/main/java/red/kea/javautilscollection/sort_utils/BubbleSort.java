package red.kea.javautilscollection.sort_utils;

/**
 * @ClassName BubbleSort
 * @Author KeA
 * @Date 2019/7/1 15:13
 * @Version 1.0
 * @Describe 冒泡排序：冒泡排序无疑是最为出名的排序算法之一，从序列的一端开始往另一端冒泡，依次比较相邻的两个数的大小
 * @Result 冒泡的代码还是相当简单的，两层循环，外层冒泡轮数，里层依次比较。时间复杂度为O(n^2)
 *
 * https://mp.weixin.qq.com/s/FVOllQoELEK3rvjpdLAxIQ
 */
public class BubbleSort {
    /**
     * 冒泡排序(BubbleSort)
     * @param arr
     * @return
     */
    public static int[] sort(int[] arr){
        for (int i = 0 ; i < arr.length - 1 ; i++){
            for(int j = 0 ; j < arr.length - 1 - i ; j++){
                int temp = 0;
                if (arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 冒泡排序(BubbleSort) 优化
     * @param arr
     * @return
     *
     * 冒泡有一个最大的问题就是这种算法不管不管你有序还是没序，闭着眼睛把你循环比较了再说。
     *
     * 比如我举个数组例子：[ 5，6，7，8，9 ]，一个有序的数组，根本不需要排序，它仍然是双层循环一个不少的把数据遍历干净，这其实就是做了没必要做的事情，属于浪费资源。
     *
     * 针对这个问题，我们可以设定一个临时遍历来标记该数组是否已经有序，如果有序了就不用遍历了。
     */
    public static int[] sortOptimize(int arr[]){
        for(int i = 0 ; i < arr.length-1 ; i++){
            boolean isSort = true;
            for(int j = 0 ; j < arr.length - 1 - i; j++){
                int temp = 0;
                if (arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSort = false;
                }
            }
            if (isSort){
                break;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {8,3,4,5,2};
        sort(arr);
    }
}
