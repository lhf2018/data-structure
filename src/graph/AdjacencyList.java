package graph;

/**
 * 使用链表实现邻接表有向图
 */
public class AdjacencyList {
    /**
     * 节点信息
     */
    class Node{
        int index;//顶点的序号
        int weight;//以该顶点为终点的边的权值
        Node next;//下一个节点
    }
    // 邻接表中表的顶点
    class VNode{
        char data;
        Node first;
    }
    private VNode[] mVexs; //顶点数组

    public AdjacencyList(char[] vexs,char[][] edges) {
        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;
        int elen = edges.length;

        //初始化“顶点”
        mVexs=new VNode[vlen];
        for(int i=0;i<mVexs.length;i++){
            mVexs[i]=new VNode();
            mVexs[i].data=vexs[i];
            mVexs[i].first=null;
        }
        // 初始化"边"
        for (int i = 0; i < elen; i++){
            // 读取边的起始顶点和结束顶点
            char c1=edges[i][0];
            char c2=edges[i][1];

            int weight=edges[i][2];
            // 读取边的起始顶点和结束顶点
            int p1=getPosition(c1);
            int p2=getPosition(c2);
            Node node=new Node();
            node.index=p2;
            node.weight=weight;

            if(mVexs[p1].first==null){
                mVexs[p1].first=node;
            }else {
                Node temp=mVexs[p1].first;
                while (temp.next!=null){
                    temp=temp.next;
                }
                temp.next=node;
            }

        }
    }
    private void print(){
        System.out.printf("List Graph:\n");
        for(int i=0;i<mVexs.length;i++){
            System.out.print(mVexs[i].data+" ");
            Node temp=mVexs[i].first;
            while (temp!=null){
                System.out.print(temp.index+" : "+temp.weight+"--->");
                temp=temp.next;
            }
            System.out.println();
        }
    }

    private int getPosition(char c) {
        for(int i=0;i<mVexs.length;i++){
            if(mVexs[i].data==c){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'C',14},
                {'A', 'D',2},
                {'A', 'F',9},
                {'B', 'C',7},
                {'C', 'D',3},
                {'D', 'E',5},
                {'D', 'F',4},
                {'E', 'G',1},
                {'F', 'G',12}};
        AdjacencyList list=new AdjacencyList(vexs,edges);
        list.print();
    }
}
