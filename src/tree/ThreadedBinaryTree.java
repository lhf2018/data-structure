package tree;

/**
 *
 * 线索二叉树的实现
 */

/*
 * 定义线索二叉树的节点
 */
class Node{
    //0为左孩子，1为前驱节点
    int ltag=0;
    //0为右孩子，1为后继节点
    int rtag=0;
    //左孩子或者前驱结点
    Node left=null;
    //右孩子或后继节点
    Node right=null;
    int val;
    Node(int val){
        this.val=val;
    }
}

public class ThreadedBinaryTree {
    private Node root; //根节点
    private int size;          // 大小
    private Node pre = null;   // 线索化的时候保存前驱

    public ThreadedBinaryTree()
    {
        this.root = null;
        this.size = 0;
        this.pre = null;
    }
    //
    public ThreadedBinaryTree(int[] data)
    {
        this.pre = null;
        this.size = data.length;
        this.root = createTree(data, 1);   // 创建二叉树
    }
    /*
    创建二叉树
     */

    public Node createTree(int[] data, int index) {
        if(index>data.length){
            return null;
        }
        Node node=new Node(data[index-1]);
        Node left=createTree(data,2*index);
        Node right=createTree(data,2*index+1);
        node.left=left;
        node.right=right;
        return node;
    }
    /*
    中序遍历线索化二叉树
     */
    public void inThread(Node root){
        if(root!=null){
            inThread(root.left);// 线索化左孩子
            if(null==root.left){// 左孩子为空
                root.ltag=1;//将左孩子设置为线索
                root.left=pre;
            }
            //pre保存上一个节点，如果上一个节点不为空且没有右孩子，那么把当前节点设置为pre所指节点的后继节点
            if(pre!=null&&null==pre.right){// 右孩子为空
                pre.rtag=1;
                pre.right=root;//设置当前节点为之前节点的后继节点
            }
            pre=root;
            inThread(root.right);
        }
    }
    /**
     * 中序遍历线索二叉树,不使用递归的情况下遍历
     *
     */
    public void inThreadList(Node root){
        if(root!=null){
            while (root!=null&&root.ltag!=1){
                root=root.left;
            }
            do{
                System.out.print(root.val + ",");
                if(root.rtag==1){   // 如果右孩子是线索
                    root=root.right;
                }else {
                    //如果有右孩子
                    root=root.right;
                    //如果右节点有左孩子
                    while (root!=null&&root.ltag==0){
                        root=root.left;
                    }
                }
            }while (root!=null);
        }
    }
    /**
     * 中序遍历
     *
     */
    public void inList(Node root)
    {
        if (root != null)
        {
            inList(root.left);
            System.out.print(root.val + ",");
            inList(root.right);
        }
    }

    /**
     * 测试正常中序遍历和使用线索化遍历
     * @param args
     */
    public static void main(String[] args) {
        int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ThreadedBinaryTree threadedBinaryTree=new ThreadedBinaryTree(data);//创建普通二叉树
        threadedBinaryTree.inList(threadedBinaryTree.root);//中序遍历二叉树
        System.out.println();//分隔符
        threadedBinaryTree.inThread(threadedBinaryTree.root);//采用中序遍历将二叉树线索化
        threadedBinaryTree.inThreadList(threadedBinaryTree.root);//中序遍历线索化二叉树

    }
}
