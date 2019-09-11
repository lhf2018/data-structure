package sort;

/**
 * 本例实现了堆排序
 * 参考博客：https://blog.csdn.net/zdp072/article/details/44227317
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = { 50, 10, 90, 30, 70, 40, 80, 60, 20 };
        System.out.println("排序之前：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        // 堆排序
        heapsort(arr);

        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }
    public static void heapsort(int[] arr){
        // 将待排序的序列构建成一个大顶堆
        for (int i = arr.length / 2; i >= 0; i--){
            heapAdjust(arr, i, arr.length);
        }
        // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
        for(int i=arr.length-1;i>0;i--){
            swap(arr,0,i);//将堆顶记录和当前未排序子序列的最后一个元素交换，使其成为最大顶堆
            heapAdjust(arr,0,i);// 交换之后，需要重新检查堆是否符合大顶堆，不符合则要调整
        }
    }

    /*
    交换元素位置
     */
    private static void swap(int[] arr, int i, int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    // 获取到左孩子结点
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * 构建堆的过程
     * @param arr 数组
     * @param i 需要构建堆的根节点
     * @param length 数组长度
     */
    private static void heapAdjust(int[] arr, int i, int length) {
        int child;
        int father;
        for(father=arr[i];leftChild(i)<length;i=child){
            child=leftChild(i);
//            如果左子树小于右子树，则需要比较右子树和父节点
            if(child!=length-1&&arr[child]<arr[child+1]){
                child++;
            }
            // 如果父节点小于孩子结点，则需要交换
            if(father<arr[child]){
                arr[i]=arr[child];
            }else {
                break;
            }
        }
        arr[i]=father;
    }
}
