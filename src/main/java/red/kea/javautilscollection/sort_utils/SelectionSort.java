package red.kea.javautilscollection.sort_utils;

/**
 * @ClassName SelectionSort
 * @Author KeA
 * @Date 2019/7/1 15:54
 * @Version 1.0
 * @Describe 选择排序：找出这个数组中最小的元素，使之与数组的第一个元素进行交换。在剩下的数组中选出最小的元素，和数组的第二个元素进行交换
 * @Result 双层循环，时间复杂度和冒泡一模一样，都是O(n^2)。
 */
public class SelectionSort {
    /**
     * 选择排序(SelectionSort)
     * @param arr
     * @return
     */
    public static int[] sort(int arr[]){
        for (int i = 0 ; i<arr.length ; i++){
            int min = i;
            for (int j = i+1 ; j<arr.length ; j++){
                if (arr[j] < arr[min]){
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] arr = {8,5,3,4,2};
        sort(arr);
    }
}
