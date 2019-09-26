package generalizedtable;

import java.util.ArrayList;
import java.util.List;

/**
 * 本例使用Node节点记录每一个稀疏矩阵中不为零的元素的行、列、数，放入list中保存
 */
public class SpanMartrix {
    class Node{
        int row;//行
        int col;//列
        int data;//数据

        Node(int row, int col, int data) {
            this.row = row;
            this.col = col;
            this.data = data;
        }
    }
    private List<Node> list; //保存Node信息

    public SpanMartrix() {
        list=new ArrayList<>();
    }

    /**
     * 保存稀疏矩阵中的元素信息
     * @param matrix 原始稀疏矩阵
     */
    public void excute(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]!=0){
                    Node node=new Node(i,j,matrix[i][j]);
                    list.add(node);
                }
            }
        }
    }

    /**
     * 利用list中的信息，恢复成稀疏矩阵
     * @param r 原始矩阵的行
     * @param c 原始矩阵的列
     * @return 恢复的稀疏矩阵
     */
    public int[][] restore(int r,int c){
        int[][] array=new int[r][c];
        for(int i=0;i<list.size();i++){
            Node node=list.get(i);
            array[node.row][node.col]=node.data;
        }
        return array;
    }
    //测试
    public static void main(String[] args) {
        SpanMartrix spanMartrix=new SpanMartrix();
        int[][] matrix=new int[][]{
                {1,4,0,0},
                {0,2,0,0},
                {1,0,2,0},
                {0,0,0,9}
        };
        spanMartrix.excute(matrix);
        int[][] array=spanMartrix.restore(matrix.length,matrix[0].length);
        int num=0;//统计原始矩阵和恢复的矩阵有多少元素不同的个数
        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if(array[i][j]!=matrix[i][j]){
                    num++;
                }
            }
        }
        System.out.println("恢复的矩阵和原矩阵不相等的个数: "+num);
    }
}
