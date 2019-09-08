package sort;

import java.util.Arrays;

/**
 * 本例实现希尔排序
 * 参考博客：https://www.cnblogs.com/chengxiao/p/6104371.html
 */
public class ShellSort {
    public static void sort(int[] arr){
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
                int j=i;
                while (j-gap>=0&&arr[j]<arr[j-gap]){
                    swap(arr,j,j-gap);
                    j-=gap;
                }
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }

    public static void main(String[] args) {
        int []arr ={1,4,2,7,5,9,8,3,6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
