package sort;

import java.util.Arrays;

/**
 * 直接插入排序(Straight Insertion Sort)
 * 参考博客：https://www.cnblogs.com/skywang12345/p/3596881.html#a43
 */
public class StraightInsertionSort {
    /**
     * 直接插入排序
     * @param a --待排序的数组
     * @param n --数组的长度
     */
    public static void insertSort(int[] a, int n){
        int i,j,k;
        for(i=1;i<n;i++){
            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            for(j=i-1;j>=0;j--){
                if(a[j]<a[i]){
                    break;
                }
            }
            //如找到了一个合适的位置
            if(j!=i-1){
                //将比a[i]大的数据向后移
                int temp=a[i];
                for(k=i-1;k>j;k--){
                    a[k+1]=a[k];
                }
                //将a[i]放到正确位置上
                a[j+1]=temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {20,40,30,10,60,50};
        insertSort(a,a.length);
        System.out.println(Arrays.toString(a));
    }
}
