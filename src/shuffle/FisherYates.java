package shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FisherYates算法
 * 思路：其基本思想就是从原始数组中随机取一个之前没取过的数字到新的数组中
 */
public class FisherYates {
    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6,7,8,9,10,11,12,13};
        int[] res=shuffle(array);
        System.out.println(Arrays.toString(res));
    }
    public static int[] shuffle(int[] array){
        int[] temp=array.clone();
        List<Integer> list=new ArrayList<>();
        int n=array.length;
        while (n>0){
            int i=(int)(Math.random()*array.length);
            if(temp[i]!=0){
                list.add(temp[i]);
                temp[i]=0;
                n--;
            }
        }
        int[] res=new int[array.length];
        for(int i=0;i<array.length;i++){
            res[i]=list.get(i);
        }
        return res;
    }
}
