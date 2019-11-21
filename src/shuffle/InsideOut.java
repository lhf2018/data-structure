package shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Inside-Out Algorithm 算法
 * 思路：是从前向后扫描数据，
 * 把位置i的数据随机插入到前i个（包括第i个）位置中（假设为k），
 * 这个操作是在新数组中进行，然后把原始数据中位置k的数字替换新数组位置i的数字。
 */
public class InsideOut {
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
        for(int i=0;i<array.length;i++){
            int index=(int)(Math.random()*i);
            list.add(index,list.get(i));
            list.remove(i+1);
        }
        int[] res=new int[array.length];
        for(int i=0;i<array.length;i++){
            res[i]=list.get(i);
        }
        return res;
    }
}
