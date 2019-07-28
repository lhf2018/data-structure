package tree;

/**
 * 本例实现二叉搜索树，二叉搜索树的中序遍历就是从小到大的排序
 */
public class BinaryTree {
    class Node{
        /**
         * 关键字/索引（识别数据用）
         */
        private int id;
        private int data;
        private Node leftChild;
        private Node rightChild;

        public Node(int id, int data) {
            this.id = id;
            this.data = data;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
        @Override
        public String toString() {
            return "Node [id=" + id + ", data=" + data + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
        }
    }
    /**
     * 二叉树
     */
    //根节点
    public Node root;

    /**
     * 判断id值大小查找节点
     */
    public Node find(int key){
        Node current=root;
        while (current.getId()!=key){
            //如果key小于当前节点，就去找左边比当前小的节点
            if(current.getId()>key){
                current=current.getLeftChild();
            }else if(current.getId()<key){//如果key大于当前节点，就去找右边比当前大的节点
                current=current.getRightChild();
            }
            if(current==null){
                return null;
            }

        }
        return current;
    }
    /**
     * 插入节点
     */
    public void insert(int id,int data){
        Node newNode=new Node(id,data);
        if(root==null){
            root=newNode;
        }else {
            Node current=root;
            Node parent=null;
            while (true){
                parent=current;
                if(id<current.getId()){
                    current=current.getLeftChild();
                    if(current==null){
                        parent.setLeftChild(newNode);
                        return;
                    }
                }else{
                    current=current.getRightChild();
                    if(current==null){
                        parent.setRightChild(newNode);
                        return;
                    }
                }
            }
        }
    }
    /**
     * 前序遍历
     */
    public void preOrder(Node node){
        if(node==null){
            return;
        }
            System.out.print(node.getId()+"-");
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
    }
    /**
     * 中序遍历
     */
    public void inOrder(Node node){
        if(node==null){
            return;
        }
            inOrder(node.getLeftChild());
            System.out.print(node.getId()+"-");
            inOrder(node.getRightChild());
    }
    /**
     * 后序遍历
     */
    public void postOrder(Node node){
        if(node!=null){
            postOrder(node.getLeftChild());
            postOrder(node.getRightChild());
            System.out.println(node.getData()+"-");
        }
    }
    /**
     * 获取最小节点数据（使劲往左边找）
     */
    public Node getMinNode(){
        Node current=root;
        Node min=null;
        while (current!=null){
            min=current;
            current=current.getLeftChild();
        }
        return min;
    }
    /**
     * 获取最大节点数据（使劲往右边找）
     */
    public Node getMaxNode(){
        Node current=root;
        Node max=null;
        while (current!=null){
            max=current;
            current=current.getRightChild();
        }
        return max;
    }
    /**
     * 删除一个节点
     * 算法是：找到被删除节点的右子节点，然后查找这个右子节点下的最后一个左子节点，
     * 也就是这颗子树的最小值节点，这就是被删除节点的中序后继节点。
     *  三种情况：
     *  1.没有子节点
     *  2.只有一个子节点
     *  3.有两个子节点
     */
    public boolean delete(int key){
        //先找到改节点
        Node current=root;
        Node parent=root;
        boolean isLeftNode=true;
        while (current.getId()!=key){
            parent=current;
            if(current.getId()>key){
                isLeftNode=true;
                current=current.getLeftChild();
            }else if(current.getId()<key){
                isLeftNode=false;
                current=current.getRightChild();
            }
            if(current==null){
                return false;
            }
        }
        //没有子节点
        if(current.getLeftChild()==null&&current.getRightChild()==null){
            if(current==root){//如果是根节点
                root=null;
            }else{//如果不是根节点
                if(!isLeftNode){
                    //如果是右节点
                    parent.setRightChild(null);
                }else{
                    //如果是左节点
                    parent.setLeftChild(null);
                }

            }
        }
        //只有一个节点
        else if(current.getRightChild()==null){//只有左节点
            if(current==root){
                root=current.getLeftChild();
            }else{
                if(isLeftNode){
                    parent.setLeftChild(current.getLeftChild());
                }else{
                    parent.setRightChild(current.getLeftChild());
                }
            }
        }else if(current.getLeftChild()==null){//只有右节点
            if(current==root){
                root=current.getRightChild();
            }else{
                if(isLeftNode){
                    parent.setLeftChild(current.getRightChild());
                }else{
                    parent.setRightChild(current.getRightChild());
                }
            }
        }
        //有两个节点
        else{
            Node successor=getSuccessor(current);
            if(current==root){
                root=successor;
            }else{
                if(isLeftNode){
                    parent.setLeftChild(successor);
                }else{
                    parent.setRightChild(successor);
                }
            }
        }
        return true;
    }
    /**
     * 找到要删除节点的中序后继节点
     * 算法是：找到被删除节点的右子节点，然后查找这个右子节点下的最后一个左子节点，
     *    也就是这颗子树的最小值节点，这就是被删除节点的中序后继节点。
     */
    private Node getSuccessor(Node node) {
        Node successor=node;
        Node successorParent=node;
        Node current=node.getRightChild();
        //查找右子树最后一个左节点
        while (current!=null){
            successorParent=successor;
            successor=current;
            current=current.getLeftChild();
        }
        if(successor!=node.getLeftChild()){
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(node.getRightChild());
        }
        return successor;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(6, 212);
        tree.insert(5, 211);
        tree.insert(8, 221);
        tree.insert(3, 321);
        tree.insert(7, 421);
        tree.insert(9, 521);
        tree.inOrder(tree.find(6));
        System.out.println(tree.getMinNode());
        System.out.println(tree.getMaxNode());
        tree.delete(5);
        tree.inOrder(tree.find(6));
    }

}
