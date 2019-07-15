package graph;

/**
 * prim算法生成最小生成树
 * 使用邻接矩阵（二维数组）
 */
public class Prim {
    private char[] mVexs;//顶点集合
    private int[][] mMatrix;//邻接矩阵
    private static final int INF=Integer.MAX_VALUE;
    /*
     * 创建图(用已提供的矩阵)
     *
     * 参数说明：
     *     vexs  -- 顶点数组
     *     matrix-- 矩阵(数据)
     */
    public Prim(char[] Vexs, int[][] Matrix){
//        初始化顶点数和边数
        int vlen=Vexs.length;

//        初始化顶点
        mVexs=new char[vlen];
        for(int i=0;i<mVexs.length;i++){
            mVexs[i]=Vexs[i];
        }
        mMatrix=new int[vlen][vlen];
        for(int i=0;i<vlen;i++){
            for(int j=0;j<vlen;j++){
                mMatrix[i][j]=Matrix[i][j];
            }
        }
    }

    /*
     *prim最小生成树
     *  start---从图中的第start个元素开始，生成最小生成树
     */
    public void Prim(int start){
        int num=mVexs.length;       //顶点个数
        int index=0;                //prim最小生成树的索引
        char[] prims=new char[num]; //prim最小生成树的结果数组
        int[] weights=new int[num]; //顶点边的权值
//      char生成的顺序
        prims[index++]=mVexs[start];
        // 初始化"顶点的权值数组"，
        // 将每个顶点的权值初始化为"第start个顶点"到"该顶点"的权值。
        for(int i=0;i<num;i++){
            weights[i]=mMatrix[start][i];
        }
        weights[start]=0;

        //最外层循环是扫过所有的节点
        for(int i=0;i<num;i++){
            if(start==i){
                continue;
            }
            int j=0;
            int k=0;
            int min=INF;//为了找到最小的节点k
//            这一层循环是为了查看当前节点在未被加入到最小生成树的顶点中，找出权值最小的顶点，也就要找到下一个顶点。
            while (j<num){
                if(weights[j]!=0&&weights[j]<min){
                    min=weights[j];
                    k=j;
                }
                j++;
            }
            // 经过上面的处理后，在未被加入到最小生成树的顶点中，权值最小的顶点是第k个顶点。
            // 将第k个顶点加入到最小生成树的结果数组中
            prims[index++]=mVexs[k];
            // 将"第k个顶点的权值"标记为0，意味着第k个顶点已经排序过了(或者说已经加入了最小树结果中)。
            weights[k]=0;
            // 当第k个顶点被加入到最小生成树的结果数组中之后，更新其它顶点的权值。
            for(j=0;j<num;j++){
//                当第j个节点没有被处理，并且被需要更新时才被更新，当前节点距离第j个节点比之前距离这个节点更近的时候才会更新
                if(weights[j]!=0&&weights[j]>mMatrix[k][j]){
                    weights[j]=mMatrix[k][j];
                }
            }
        }
//        计算最小生成树的权值
        int sum=0;
        for(int i=1;i<index;i++){
            int min=INF;
            int n=getPosition(prims[i]);//获取prims[i]在mMatrix中的位置
            // 在mVexs[0...i]中，找出到j的权值最小的顶点。只有在prims前面的顶点才有可能连着prims[i]，后面的边由后面的顶点去计算
            for(int j=0;j<i;j++){
                int m=getPosition(prims[j]);
                if(mMatrix[m][n]<min){
                    min=mMatrix[m][n];
                }
            }
            sum+=min;
        }
        // 打印最小生成树
        System.out.printf("PRIM(%c)=%d: ", mVexs[start], sum);
        for (int i = 0; i < index; i++)
            System.out.printf("%c ", prims[i]);
        System.out.printf("\n");

    }

    private int getPosition(char ch) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i]==ch)
                return i;
        return -1;
    }
    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                        /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        Prim pG;

        // 采用已有的"图"
        pG = new Prim(vexs, matrix);
        pG.Prim(0);   // prim算法生成最小生成树
    }
}
