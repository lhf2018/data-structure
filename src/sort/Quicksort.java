package sort;

/**
 * 快速排序
 * 方法一：指针交换法
 */
public class Quicksort {
    /**
     * 方法一：指针交换法
     * 链接：http://www.sohu.com/a/246785807_684445
     */
    private static void subSort1(int[] array, int start, int end) {
        if(start<end){
            int key = array[start];
            // i从左边搜索，搜索大于分界值的元素的索引
            int i = start+1;
            // j从右边开始搜索，搜索小于分界值的元素的索引
            int j = end;
            while (true){
                while (j>start&&array[j]>=key){
                    j--;
                }
                // 找到小于分界值的元素的索引，或者i已经到了end处
                while (i<end&&array[i]<=key){
                    i++;
                }
                if(i<j){
                    swap(array,i,j);
                }else {
                    break;
                }
            }
            swap(array,start,j);
            //递归左边子序列
            subSort1(array, start, j - 1);
            //递归右边子序列
            subSort1(array, j + 1, end);
        }
    }
    //交换left和right的值
    private static void swap(int[] array,int left, int right) {
        int temp=array[right];
        array[right]=array[left];
        array[left]=temp;
    }

    public static void main(String[] args) {
        int[] array={12,4,6,7,1,16,22,2,5,9,13,10};
        Quicksort.subSort1(array,0,array.length-1);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
}
