package graph;

/**
 * 本例实现有向图的邻接矩阵
 */
public class AdjacencyMatrixDG {
    public static final int MAX_WEIGHT=-1;//表示无穷大
    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵

    public AdjacencyMatrixDG(char[] vexs,char[][] edgs) {
        // 初始化"顶点数"和"边数"
        int vlen=vexs.length;
        int elen=edgs.length;

        //初始化顶点
        mVexs=new char[vlen];
        for(int i=0;i<mVexs.length;i++){
            mVexs[i]=vexs[i];
        }
        //初始化边
        mMatrix=new int[vlen][vlen];
        for(int i=0;i<elen;i++){
            int p1=getPosition(edgs[i][0]);
            int p2=getPosition(edgs[i][1]);
            int value=edgs[i][2];

            mMatrix[p1][p2]=value;
            mMatrix[p2][p1]=MAX_WEIGHT;
        }
    }

    private int getPosition(char c) {
        for(int i=0;i<mVexs.length;i++){
            if(mVexs[i]==c){
                return i;
            }
        }
        return -1;
    }
    /**
     * 打印无向图的邻接矩阵
     */
    public void print(){
        System.out.print("* ");
        for(int i=0;i<mVexs.length;i++){
            System.out.print(mVexs[i]+" ");
        }
        System.out.println();
        for(int i=0;i<mMatrix.length;i++){
            System.out.print(mVexs[i]+" ");
            for(int j=0;j<mMatrix.length;j++){
                if(mMatrix[i][j]==0){
                    System.out.print(-1+ " ");
                }else {
                    System.out.print(mMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    //测试
    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'C',14},
                {'A', 'D',2},
                {'A', 'F',9},
                {'B', 'C',7},
                {'C', 'D',3},
                {'E', 'G',1},
                {'F', 'G',12}};
        AdjacencyMatrixDG adjacencyMatrixDG=new AdjacencyMatrixDG(vexs, edges);
        adjacencyMatrixDG.print();
    }
}
