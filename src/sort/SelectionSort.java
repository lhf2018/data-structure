package sort;

import java.util.Arrays;

/**
 * 实现原理: https://blog.csdn.net/jianyuerensheng/article/details/51254311
 */
public class SelectionSort {
    public void sort(int[] array){
        if(array==null||array.length==0){
            return;
        }
        for(int i=0;i<array.length;i++){
            int temp=array[i];
            int index=i;//将当前下表定义为最小值下标
            for(int j=i+1;j<array.length;j++){
                if(array[j]<temp){
                    temp=array[j];
                    index=j;
                }
            }
            if(index!=i){
                array[index]=array[i];
                array[i]=temp;
            }

        }
    }

    public static void main(String[] args) {
        SelectionSort selectionSort=new SelectionSort();
        int[] a = { 49,38,65,97,76,13,27,49 };
        selectionSort.sort(a);
        System.out.println(Arrays.toString(a));

    }
}
