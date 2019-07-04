package red.kea.javautilscollection.sort_utils;

/**
 * @ClassName QuickSort
 * @Author KeA
 * @Date 2019/7/4 15:11
 * @Version 1.0
 */
public class QuickSort {
    /**
     * 快速排序的核心思想也是分治法，分而治之。
     * 它的实现方式是每次从序列中选出一个基准值，
     * 其他数依次和基准值做比较，
     * 比基准值大的放右边，比基准值小的放左边，
     * 然后再对左边和右边的两组数分别选出一个基准值，
     * 进行同样的比较移动，
     * 重复步骤，
     * 直到最后都变成单个元素，
     * 整个数组就成了有序的序列。
     */

    /**
     * 单边扫描
     * 快速排序的关键之处在于切分，切分的同时要进行比较和移动，这里介绍一种叫做单边扫描的做法。
     *
     *
     * 我们随意抽取一个数作为基准值，
     * 同时设定一个标记 mark 代表左边序列最右侧的下标位置，
     * 当然初始为 0 ，接下来遍历数组，
     * 如果元素大于基准值，无操作，继续遍历，如果元素小于基准值，则把 mark + 1 ，
     * 再将 mark 所在位置的元素和遍历到的元素交换位置，mark 这个位置存储的是比基准值小的数据，
     * 当遍历结束后，将基准值与 mark 所在元素交换位置即可。
     */
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }
        //切分
        int pivotIndex = partition(arr, startIndex, endIndex);
        sort(arr, startIndex, pivotIndex-1);
        sort(arr, pivotIndex+1, endIndex);
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        //取基准值
        int pivot = arr[startIndex];
        //Mark初始化为起始下标
        int mark = startIndex;

        for(int i=startIndex+1; i<=endIndex; i++){
            if(arr[i]<pivot){
                //小于基准值 则mark+1,并交换位置。
                mark ++;
                int p = arr[mark];
                arr[mark] = arr[i];
                arr[i] = p;
            }
        }
        //基准值与mark对应元素调换位置
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }


    /**
     * 双边扫描
     *
     * 另外还有一种双边扫描的做法，看起来比较直观：
     * 我们随意抽取一个数作为基准值，然后从数组左右两边进行扫描，先从左往右找到一个大于基准值的元素，
     * 将下标指针记录下来，然后转到从右往左扫描，找到一个小于基准值的元素，交换这两个元素的位置，
     * 重复步骤，直到左右两个指针相遇，再将基准值与左侧最右边的元素交换。
     */
    public static void sort1(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort1(int[] arr, int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }
        //切分
        int pivotIndex = partition1(arr, startIndex, endIndex);
        sort(arr, startIndex, pivotIndex-1);
        sort(arr, pivotIndex+1, endIndex);
    }


    private static int partition1(int[] arr, int startIndex, int endIndex) {
        int left = startIndex;
        int right = endIndex;
        int pivot = arr[startIndex];//取第一个元素为基准值

        while (true) {
            //从左往右扫描
            while (arr[left] <= pivot) {
                left++;
                if (left == right) {
                    break;
                }
            }

            //从右往左扫描
            while (pivot < arr[right]) {
                right--;
                if (left == right) {
                    break;
                }
            }

            //左右指针相遇
            if (left >= right) {
                break;
            }

            //交换左右数据
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        //将基准值插入序列
        int temp = arr[startIndex];
        arr[startIndex] = arr[right];
        arr[right] = temp;
        return right;
    }
}
