package Recursion;

public class EightQueens {
    public static int[][] arry=new int[8][8];//棋盘，存放皇后
    private static int res=0;
    /*
    寻找皇后节点
     */
    public static void findQueen(int i){
//        第i行
        if(i>7){
            res++;
            return;
        }
//        第m列
        for(int m=0;m<8;m++){
            //检查皇后摆放是否合适
            if(check(i,m)){
                arry[i][m]=1;
                findQueen(i+1);
                //清零，以免回溯的时候出现脏数据
                arry[i][m]=0;
            }
        }
    }

    /*
    判断节点是否能够被放置
     */
    private static boolean check(int i, int m) {
        /*
        因为棋盘按照每一行来摆放，所以每一行肯定只会有一个，只查看列有没有重复
         */
//        检查列
        for(int j=0;j<8;j++){
            if(arry[j][m]==1){
                return false;
            }
        }
//        检查左对角线
        for(int k=i-1,j=m-1;k>=0&&j>=0;k--,j--){
            if(arry[k][j]==1){
                return false;
            }
        }
//        检查右对角线
        for(int k=i-1,j=m+1;k>=0&&j<=7;k--,j++){
            if(arry[k][j]==1){
                return false;
            }
        }
        return true;

    }

    /*
    结果：92
     */
    public static void main(String[] args) {
        findQueen(0);
        System.out.println(res);
    }
}
