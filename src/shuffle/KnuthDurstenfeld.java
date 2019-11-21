package shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * KnuthDurstenfeld算法
 * 思路：该算法的基本思想和 Fisher 类似，每次从未处理的数据中随机取出一个数字，
 * 然后把该数字放在数组的尾部，即数组尾部存放的是已经处理过的数字。
 */
public class KnuthDurstenfeld {
    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6,7,8,9,10,11,12,13};
        int[] res=shuffle(array);
        System.out.println(Arrays.toString(res));
    }
    public static int[] shuffle(int[] array){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<array.length;i++){
            list.add(array[i]);
        }
        int n=array.length;
        while (n>0){
            int i=(int)(Math.random()*n);
            list.add(list.get(i));
            list.remove(i);
            n--;
        }
        int[] res=new int[array.length];
        for(int i=0;i<array.length;i++){
            res[i]=list.get(i);
        }
        return res;
    }
}
