package sort;

import java.util.Arrays;

/**
 * 参考文章：https://blog.csdn.net/guoweimelon/article/details/50902597
 */
public class BubbleSort {
    public void bubble(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort sort=new BubbleSort();
        int[] array={5,18,3,55,17,26,79,1,45,63,57};
        sort.bubble(array);
        System.out.println(Arrays.toString(array));
    }
}
