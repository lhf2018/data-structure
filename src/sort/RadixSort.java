package sort;

import java.util.Arrays;

/**
 * 类型：基数排序
 * 参考思路：https://blog.csdn.net/sun_star1chen/article/details/20313167
 */
public class RadixSort {
    private static void radixSort(int[] array){
        int tmp=getMax(array);
        int loop=0;//最长的位数
        do{
            loop++;
        }while ((tmp=tmp/10)>0);
        int count=1;
        int lsd;
        int[][] bubble=new int[10][array.length];
        int[] order=new int[10];//计算每一位数当前有多少个数字
        tmp=1;
        while (count <= loop) {
            for(int i=0;i<array.length;i++){
                lsd=(array[i]/tmp)%10;
                bubble[lsd][order[lsd]]=array[i];
                order[lsd]++;
            }
            int k=0;
            for(int i=0;i<10;i++){
                if(order[i]!=0){
                    for(int j=0;j<order[i];j++){
                        array[k++]=bubble[i][j];
                    }
                }
                order[i]=0;
            }
            tmp*=10;
            count++;
        }
    }

    /**
     * 返回数组最大的数
     * @param num
     * @return
     */
    private static int getMax(int[] num){
        int res=num[0];
        for(int i=1;i<num.length;i++){
            res=Math.max(num[i],res);
        }
        return res;

    }

    public static void main(String[] args) {
        int[] array={12,55,18,69,129,453,684,1,49,16};
        radixSort(array);
        System.out.println(Arrays.toString(array));
    }
}
