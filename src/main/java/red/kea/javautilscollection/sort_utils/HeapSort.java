package red.kea.javautilscollection.sort_utils;

/**
 * @ClassName HeapSort
 * @Author KeA
 * @Date 2019/7/5 14:24
 * @Version 1.0
 */
public class HeapSort {
    /**
     * 如果你了解堆这种数据结构，你应该知道堆是一种优先队列，两种实现，最大堆和最小堆，由于我们这里排序按升序排，所以就直接以最大堆来说吧。
     *
     * 我们完全可以把堆（以下全都默认为最大堆）看成一棵完全二叉树，
     * 但是位于堆顶的元素总是整棵树的最大值，每个子节点的值都比父节点小，由于堆要时刻保持这样的规则特性，
     * 所以一旦堆里面的数据发生变化，我们必须对堆重新进行一次构建。
     *
     * 既然堆顶元素永远都是整棵树中的最大值，那么我们将数据构建成堆后，
     * 只需要从堆顶取元素不就好了吗？第一次取的元素，是否取的就是最大值？
     * 取完后把堆重新构建一下，然后再取堆顶的元素，是否取的就是第二大的值？
     * 反复的取，取出来的数据也就是有序的数据。
     *
     */

    public static void sort(int[] arr) {
        int length = arr.length;
        //构建堆
        buildHeap(arr, length);
        for ( int i = length - 1; i > 0; i-- ) {
            //将堆顶元素与末位元素调换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            //数组长度-1 隐藏堆尾元素
            length--;
            //将堆顶元素下沉 目的是将最大的元素浮到堆顶来
            sink(arr, 0, length);
        }
    }
    private static void buildHeap(int[] arr, int length) {
        for (int i = length / 2; i >= 0; i--) {
            sink(arr, i, length);
        }
    }

    /**
     * 下沉调整
     * @param arr 数组
     * @param index 调整位置
     * @param length 数组范围
     */
    private static void sink(int[] arr, int index, int length) {
        //左子节点下标
        int leftChild = 2 * index + 1;
        //右子节点下标
        int rightChild = 2 * index + 2;
        //要调整的节点下标
        int present = index;

        //下沉左边
        if (leftChild < length && arr[leftChild] > arr[present]) {
            present = leftChild;
        }

        //下沉右边
        if (rightChild < length && arr[rightChild] > arr[present]) {
            present = rightChild;
        }

        //如果下标不相等 证明调换过了
        if (present != index) {
            //交换值
            int temp = arr[index];
            arr[index] = arr[present];
            arr[present] = temp;

            //继续下沉
            sink(arr, present, length);
        }
    }
    /**
     * 堆排序和快速排序的时间复杂度都一样是 O(nlogn)。
     */
}
