package tree;


/**
 * 参考博客：https://blog.csdn.net/javazejian/article/details/53892797
 */
public class AVL {
    /**
     * 定义平衡二叉树的节点
     */
    class TreeNode{
        int val;
        int height;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, int height, TreeNode left, TreeNode right) {
            this.val = val;
            this.height = height;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 左左单旋转(LL旋转) w变为x的根结点, x变为w的右子树
     */
    public TreeNode singleRotateLeft(TreeNode x){
        TreeNode w=x.left;//把w结点旋转为根结点
        x.left=w.right;//同时w的右子树变为x的左子树
        w.right=x;//x变为w的右子树
        //重新计算x/w的高度
        x.height=Math.max(height(x.left),height(x.right))+1;
        w.height=Math.max(height(w.left),x.height)+1;
        return w;//返回新节点
    }

    /**
     * 右右单旋转(RR旋转) x变为w的根结点, w变为x的左子树
     */
    public TreeNode singleRotateRight(TreeNode w){
        TreeNode x=w.right;
        w.right=x.left;
        x.left=w;
        x.height=Math.max(height(x.left),height(x.right))+1;
        w.height=Math.max(height(w.left),x.height)+1;
        return x;
    }
    /**
     * 左右旋转(LR旋转) x(根) w y 结点 把y变成根结点
     */
    public TreeNode doubleRotateWithLeft(TreeNode x){
        //w先进行RR旋转
        x.left=singleRotateRight(x.left);
        //再进行x的LL旋转
        return singleRotateLeft(x);
    }
    /**
     *
     */
    public TreeNode doubleRotateWithRight(TreeNode x){
        //先进行LL旋转
        x.right=singleRotateLeft(x.right);
        //再进行RR旋转
        return singleRotateRight(x);
    }

    /**
     * AVL树的插入操作
     * @param val 要插入的值
     * @param p 根节点
     * @return 根节点
     */
    public TreeNode insert(int val,TreeNode p){
        //说明已没有孩子结点,可以创建新结点插入了.
        if(p==null){
            p=new TreeNode(val);
        }else if(val<p.val){
            p.left=insert(val,p.left);
            //插入后计算子树的高度,等于2则需要重新恢复平衡,由于是左边插入,左子树的高度肯定大于等于右子树的高度
            if(height(p.left)-height(p.right)==2){
                //判断data是插入点的左孩子还是右孩子
                if(val<p.left.val){
                    //进行LL旋转
                    p=singleRotateLeft(p);
                }else{
                    //进行左右旋转
                    p=doubleRotateWithLeft(p);
                }
            }
        }else if (val>p.val){//向右子树寻找插入位置
            p.right=insert(val,p.right);

            if(height(p.right)-height(p.left)==2){
                if (val<p.right.val){
                    //进行右左旋转
                    p=doubleRotateWithRight(p);
                }else {
                    p=singleRotateRight(p);
                }
            }
        }
        //重新计算各个结点的高度
        p.height=Math.max(height(p.left),height(p.right))+1;
        //返回p节点
        return p;
    }
    /**
     * 平衡二叉树的删除
     */
    public TreeNode remove(int val,TreeNode p){
        if(p==null){
            return null;
        }
        int result=val-p.val;
        //从左子树查找需要删除的元素
        if(result<0){
            p.left=remove(val,p.left);
            //检测是否平衡
            if(height(p.right)-height(p.left)==2){
                TreeNode currentNode=p.right;
                //判断是哪种旋转
                if(height(currentNode.left)>height(currentNode.right)){
                    //LL
                    p=singleRotateLeft(p);
                }else{
                    //LR
                    p=doubleRotateWithLeft(p);
                }
            }
        }
        //从右子树查找需要删除的元素
        else if(result>0){
            p.right=remove(val,p.right);
            //检测是否平衡
            if(height(p.left)-height(p.right)==2){
                TreeNode currentNode=p.left;
                //判断需要那种旋转
                if(height(currentNode.right)>height(currentNode.left)){
                    //RR
                    p=singleRotateRight(p);
                }else{
                    //RL
                    p=doubleRotateWithRight(p);
                }
            }
        }
        //已找到需要删除的元素,并且要删除的结点拥有两个子节点
        else if(p.right!=null&&p.left!=null){
            //寻找替换的节点
            p.val=findMin(p.right).val;
            //移除用于替换的节点
            p.right=remove(p.val,p.right);
        }
        //只有一个孩子节点或者只是叶子节点的时候
        else{
            p=(p.left!=null)? p.left:p.right;
        }
        if(p!=null)
            p.height = Math.max(height( p.left ), height( p.right ) ) + 1;
        return p;
    }
    /**
     *     计算当前节点的高度
     */
    public int height(TreeNode p){
        return p == null ? -1 : p.height;
    }

    /**
     * 查找最小的节点
     */
    public TreeNode findMin(TreeNode p){
        if (p==null)//结束条件
            return null;
        else if (p.left==null)//如果没有左结点,那么t就是最小的
            return p;
        return findMin(p.left);
    }

}
