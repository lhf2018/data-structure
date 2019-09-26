package generalizedtable;

/**
 * 参考博客：http://data.biancheng.net/view/183.html
 */
public class SymmetricMatrixCompression {
    /**
     *  把根据斜对角线对称的矩阵 的下三角存到一维数组中；
     */
    public int[] create(int[][]  SymmetricMatrix){
        int n=SymmetricMatrix.length;
        int[] array=new int[n*(n+1)/2];
        int num=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                array[num++]=SymmetricMatrix[i][j];
            }
        }
        return array;
    }
    /**
     * 取数据
     */
    public int get(int row,int col,int[] array){
        if(row<col){
            int temp=row;
            row=col;
            col=temp;
        }
        return array[row*(row+1)/2+col];
    }
    /**
     * 恢复成对称矩阵
     */
    public int[][] restore(int[] array,int size){
        int[][] matrix=new int[size][size];
        for(int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                matrix[i][j]=get(i,j,array);
            }
        }
        return matrix;

    }

    public static void main(String[] args) {
        int[][] SymmetricMatrix=new int[][]{
                {1,2,3},
                {2,5,6},
                {3,6,9},
        };
        SymmetricMatrixCompression compression=new SymmetricMatrixCompression();
        int[] array=compression.create(SymmetricMatrix);
        int[][] matrix=compression.restore(array,SymmetricMatrix.length);
        int num=0;
        for(int i=0;i<SymmetricMatrix.length;i++){
            for (int j=0;j<SymmetricMatrix.length;j++){
                if(SymmetricMatrix[i][j]!=matrix[i][j]){
                    num++;
                }
            }
        }
        System.out.println("恢复的矩阵和原矩阵不相等的个数: "+num);

    }
}
