package generalizedtable;

/**
 * 使用链表实现的广义表类
 */
public class GeneralizedTable {
    private final int TAG_TABLE=1;
    private final int TAG_ITEM=0;

    private Node head; //头指针，指向头节点

    /**
     * 广义表节点 tag值为1证明 是子表，为0证明是数值
     */
    class Node{
        int tag;
        int data;
        GeneralizedTable child;//指向该处的子表
        Node next; //指向下一个节点

        public Node(int tag, int data) {
            this.tag = tag;
            this.data = data;
        }

        public Node(int tag, GeneralizedTable child) {
            this.tag = tag;
            this.child = child;
        }
    }
    // 构造广义表，由数组提供原子初值
    public GeneralizedTable(int[] atoms) {
        Node rear=this.head;
        for(int i=0;i<atoms.length;i++){
            rear.next=new Node(TAG_ITEM,atoms[i]);
            rear=rear.next;
        }
    }
    // 判断广义表是否为空
    public boolean isEmpty() {
        return head.next == null;
    }
    // 返回广义表长度
    public int length() {
        int i = 0;
        Node rear = this.head.next;
        while (rear != null) {
            i++;
            rear = rear.next;
        }
        return i;
    }
    // 返回广义表深度，递归方法
    public int depth(){
        int max=1;
        Node p=this.head.next;
        while (p!=null){
            if(p.child!=null){
                int d=p.child.depth();
                if(max<=d){
                    max=d+1;
                }
            }
        }
        return max;
    }
    // 插入原子作为第i个元素
    public Node insert(int i,int x){
        Node p=head;
        for(int j=0;j<i&&p.next!=null;j++){
            p=p.next;
        }
        if(p!=null){
            Node node=p.next;
            p.next=new Node(TAG_ITEM,x);
            if(node!=null){
                p.next.next=node.next;
            }
        }
        return p.next;
    }

    //插入子表作为第i个元素
    public Node insert(int i,GeneralizedTable glist){
        if(glist==null){
            return null;
        }
        Node p=head;
        for(int j=0;p.next!=null&&j<i;j++){
            p=p.next;
        }
        if(p!=null){
            Node node=p.next;
            p.next=new Node(TAG_TABLE,glist);
            if(node!=null){
                p.next.next=node.next;
            }
        }
        return p.next;
    }
    // 在广义表的最后添加原子节点
    public void append(int x) {
        insert(Integer.MAX_VALUE, x);
    }
    // 在广义表的最后添加子表
    public void append(GeneralizedTable glist) {
        this.insert(Integer.MAX_VALUE, glist);
    }
    // 返回广义表所有元素对应的字符串，形式为"(,)",广义表遍历算法，递归方法
    public String toString(){
        String str="(";
        Node p=this.head.next;
        while (p!=null){
            if(p.child==null){
                str+=p.data;
            }else {
                str+=p.child.toString();
            }
            if(p.next!=null){
                str+=",";
            }
            p=p.next;
        }
        return str+")";
    }

}
