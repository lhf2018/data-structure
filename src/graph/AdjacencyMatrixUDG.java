package graph;

/**
 * 本例实现无向图的邻接矩阵
 */
public class AdjacencyMatrixUDG {
    private char[] mVexs; //顶点集合
    private int[][] mMatrix; //邻接矩阵
    /**
     * 创建图（用已提供的矩阵）
     * vexs  -- 顶点数组
     * edges -- 边数组
     */
    public AdjacencyMatrixUDG(char[] vexs,char[][] edgs){
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

            mMatrix[p1][p2]=1;
            mMatrix[p2][p1]=1;
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
                System.out.print(mMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'F', 'G'}};
        AdjacencyMatrixUDG adjacencyMatrixUDG=new AdjacencyMatrixUDG(vexs,edges);
        adjacencyMatrixUDG.print();

    }
}
