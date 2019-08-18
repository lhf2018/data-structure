package tree;

public class RBTree {
    private RBTNode mRoot;
    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    /**
     * 红黑树节点
     */
    class RBTNode{
        boolean color;//颜色
        int key;//键值
        RBTNode left;//左孩子
        RBTNode right;//右孩子
        RBTNode parent;//父节点

        public RBTNode(boolean color, int key, RBTNode left, RBTNode right, RBTNode parent) {
            this.color = color;
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
    //返回当前节点的parent节点
    public RBTNode parentOf(RBTNode node){
        return node!=null?node.parent:null;
    }
    //返回颜色
    public boolean colorOf(RBTNode node){
        return node!=null?node.color:BLACK;
    }
    public boolean isRed(RBTNode node){
        return (node != null) && (node.color == RED);
    }
    public boolean isBlack(RBTNode node){
        return !isRed(node);
    }

    /**
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)-.           / \                #
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     *
     */
    public void leftRotate(RBTNode x){
        //设x的右孩子为y
        RBTNode y=x.right;
        // 将 “y的左孩子” 设为 “x的右孩子”；
        // 如果y的左孩子非空，将 “x” 设为 “y的左孩子的父亲”
        x.right=y.left;
        if(y.left!=null){
            y.left.parent=x;
        }
        // 将 “x的父亲” 设为 “y的父亲”
        y.parent=x.parent;
        if(x.parent==null){
            this.mRoot=y;    // 如果 “x的父亲” 是空节点，则将y设为根节点
        }else {
            if(x.parent.left==x){
                x.parent.left=y;    // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
            }else {
                x.parent.right=y;   // 如果 x是它父节点的右孩子，则将y设为“x的父节点的右孩子”
            }
        }
        //将 “x” 设为 “y的左孩子”
        y.left=x;
        // 将 “x的父节点” 设为 “y”
        x.parent=y;
    }
    /**
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \      --(右旋)-.            /  \                     #
     *        x   ry                           lx   y
     *       / \                                   / \                   #
     *      lx  rx                                rx  ry
     *
     */
    public void rightRotate(RBTNode y){
        //设置x是当前节点的左孩子
        RBTNode x=y.left;
        // 将 “x的右孩子” 设为 “y的左孩子”；
        // 如果"x的右孩子"不为空的话，将 “y” 设为 “x的右孩子的父亲”
        y.left=x.right;
        if(x.right!=null){
            x.right.parent=y;
        }
        // 将 “y的父亲” 设为 “x的父亲”
        x.parent=y.parent;
        if(y.parent!=null){
            this.mRoot=y;
        }else {
            if(y==y.parent.right){
                y.parent.right = x;
            }else{
                y.parent.left=x;
            }
        }
        // 将 “y” 设为 “x的右孩子”
        y=x.right;
        // 将 “y的父节点” 设为 “x”
        y.parent=x;

    }
    /**
     * 将结点插入到红黑树中
     *
     */
    public void insert(RBTNode node){
        if(node==null){
            return;
        }
        int cmp;
        RBTNode y=null;
        RBTNode x=mRoot;
        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x!=null){
            y=x;//记录上一个节点
            cmp=node.key-x.key;
            if(cmp<0){
                x=x.left;
            }else {
                x=x.right;
            }
        }
        node.parent=y;
        if(y!=null){
            cmp = node.key-y.key;
            if(cmp<0){
                y.left=node;
            }else {
                y.right=node;
            }
        }else {
            this.mRoot=node;
        }
        // 2. 设置节点的颜色为红色
        node.color = RED;
        // 3. 将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }
    /*
     * 新建结点(key)，并将其插入到红黑树中
     *
     * 参数说明：
     *     key 插入结点的键值
     */
    public void insert(int key) {
        RBTNode node=new RBTNode(BLACK, key,null,null,null);

        // 如果新建结点失败，则返回。
        if (node != null)
            insert(node);
    }
    /**
     * 红黑树插入修正函数
     *
     * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     *
     */
    public void insertFixUp(RBTNode node){
        RBTNode parent,gparent;
        //若“父节点存在，并且父节点的颜色是红色”
        while ((parent=parentOf(node))!=null&&isRed(parent)){
            gparent=parentOf(parent);
            //若“父节点”是“祖父节点的左孩子”
            if(parent==gparent.left){
                // Case 1条件：叔叔节点是红色
                RBTNode uncle=gparent.right;
                if((uncle!=null)&&isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node=gparent;
                    continue;
                }
                // Case 2条件：叔叔是黑色，且当前节点是右孩子
                if(parent.right==node){
                    //这一步只完成了parent左旋，并且交换了父节点与当前节点的称呼，因为第二case2与case3有部分重叠
                    RBTNode tmp;
                    leftRotate(parent);
                    tmp=parent;
                    parent=node;
                    node=tmp;
                }
                // Case 3条件：叔叔是黑色，且当前节点是左孩子。
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            }else{//若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                RBTNode uncle=gparent.left;
                if((uncle!=null)&&isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node=gparent;
                    continue;
                }
                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if(parent.left==node){
                    RBTNode tmp;
                    rightRotate(parent);
                    tmp=parent;
                    parent=node;
                    node=tmp;
                }
                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);

            }
        }
        // 将根节点设为黑色
        setBlack(this.mRoot);
    }
    /**
     * 删除结点(node)，并返回被删除的结点
     *
     * 参数说明：
     *     node 删除的结点
     */
    private void remove(RBTNode node) {
        RBTNode child,parent;
        boolean color;
        // 被删除节点的"左右孩子都不为空"的情况。
        if((node.left!=null)&&(node.right!=null)){
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            RBTNode replace=node;
            //获取后继节点
            replace=replace.right;
            while (replace.left!=null){
                replace=replace.left;
            }
            // "node节点"不是根节点(只有根节点不存在父节点)
            if(parentOf(node)!=null){
                if(parentOf(node).left==node){
                    parentOf(node).left=replace;
                }else {
                    parentOf(node).right=replace;
                }
            }else {
                // "node节点"是根节点，更新根节点。
                this.mRoot=replace;
            }
            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child=replace.right;
            parent=parentOf(replace);
            //保存取代节点的颜色
            color=colorOf(replace);
            // "被删除节点"是"它的后继节点的父节点"
            if(parent==node){
                parent=replace;
            }else {
                //child不为空
                if(child!=null){
                    setParent(child,parent);
                }
                parent.left=child;
                replace.right=node.right;
                setParent(node.right,replace);
            }
            replace.parent=node.parent;
            replace.color=node.color;
            replace.left=node.left;
            node.left.parent=replace;
            if(color==BLACK){
                removeFixUp(child,parent);
            }
            node=null;
            return;
        }
        if (node.left !=null) {
            child = node.left;
        } else {
            child = node.right;
        }
        parent = node.parent;
        // 保存"取代节点"的颜色
        color = node.color;
        if(child!=null){
            child.parent=parent;
        }
        // "node节点"不是根节点
        if(parent!=null){
            if(parent.left==node){
                parent.left=child;
            }else {
                parent.right=child;
            }
        }else {
            this.mRoot=child;
        }
        if(color==BLACK){
            removeFixUp(child,parent);
        }
        //删除这个点
        node=null;
    }

    private void setParent(RBTNode child, RBTNode parent) {
        if(child!=null){
            child.parent=parent;
        }
    }

    /**
     * 删除结点(z)，并返回被删除的结点
     *
     * 参数说明：
     *     tree 红黑树的根结点
     *     z 删除的结点
     */
    public void remove(int key){
        RBTNode node;
        if((node=search(key))!=null){
            remove(node);
        }
    }
    /**
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明：
     *     node 待修正的节点
     */
    private void removeFixUp(RBTNode node,RBTNode parent){
        RBTNode other;
        while ((node==null||isBlack(node)&&node!=this.mRoot)){
            if(parent.left==node){
                other=parent.right;
                if(isRed(other)){
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other=parent.right;
                }
                if((other.left==null||isBlack(other.left))&&
                        (other.right==null||isBlack(other.right))){
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node=parent;
                    parent=parentOf(node);
                    //下一步继续处理
                }else {
                    if(other.right==null||isBlack(other.right)){
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other=parent.right;//设置兄弟节点
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node=this.mRoot;
                    break;
                }
            }else {
                other=parent.left;
                if(isRed(other)){
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other=parent.left;
                }
                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left == null || isBlack(other.left)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.mRoot;
                    break;
                }
            }
        }
        if(node!=null){
            setBlack(node);
        }
    }

    private void setColor(RBTNode node, boolean color) {
        if(node!=null){
            node.color=color;
        }
    }
    //查找指定值，内部接口
    private RBTNode search(RBTNode x, int key) {
        if(x==null){
            return x;
        }
        int cmp=key-x.key;
        if(cmp<0){
            return search(x.left,key);
        }else if(cmp>0){
            return search(x.right,key);
        }else {
            return x;
        }
    }
    //查找指定值，外部接口
    public RBTNode search(int key){
        return search(mRoot,key);
    }


    private void setRed(RBTNode node) {
        if(node!=null){
            node.color=RED;
        }
    }
    private void setBlack(RBTNode node) {
        if(node!=null){
            node.color=BLACK;
        }
    }
}
