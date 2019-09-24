package linearlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目名称：求两个数组区间的交集
 *
 * 题目描述：给定两个由一些闭区间组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 *返回这两个区间列表的交集。
 *
 * 1.使用双指针，比较每个指针所指区间段的最小值，选取大的左区间去比较左区间值小的区间的右区间，看看两者是否有交集，如果有交集，就把相交的部分放入一个list，没有则跳过
 * 2.比较右区间大小，右区间小的指针加1.
 * 3.最后把list赋值给一个二维数组。
 *
 */
public class ArrayInterval {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list=new ArrayList<>();
        int len_A=A.length;
        int len_B=B.length;
        int p1=0;
        int p2=0;
        /*
        分别判断每个区间的交集
         */
        while(p1<len_A&&p2<len_B){
            if(A[p1][0]<=B[p2][0]){
                if(A[p1][1]>=B[p2][1]){
                    list.add(new int[]{B[p2][0],B[p2][1]});
                }else if(A[p1][1]>=B[p2][0]&&A[p1][1]<B[p2][1]){
                    list.add(new int[]{B[p2][0],A[p1][1]});
                }
            }else if(A[p1][0]>B[p2][0]){
                if(B[p2][1]>=A[p1][1]){
                    list.add(new int[]{A[p1][0],A[p1][1]});
                }else if(B[p2][1]>=A[p1][0]&&B[p2][1]<A[p1][1]){
                    list.add(new int[]{A[p1][0],B[p2][1]});
                }
            }
            if(A[p1][1]>B[p2][1]){
                p2++;
            }else{
                p1++;
            }
        }
        /*
        list赋值给数组
         */
        int[][] res=new int[list.size()][2];
        for(int i=0;i<res.length;i++){
            res[i][0]=list.get(i)[0];
            res[i][1]=list.get(i)[1];
        }
        return res;
    }
}
