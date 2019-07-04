package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
/**
 * 本例子实现了哈夫曼树
 * 其中排序使用快速排序
 */

import static java.util.Collections.swap;

public class Huffman_Tree {
    /**
     * 定义哈夫曼树的节点
     * @param <T>
     */
    public static class Node<T>{
        T data;
        double weight;//权值
        Node left;//左孩子
        Node right;//右孩子

        public Node(T data, double weight) {
            super();
            this.data = data;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node[data=" + data + ", weight=" + weight + "]";
        }
    }
    /**
     * 构造哈夫曼树
     */
    public static Node createTree(List<Node> nodes){
        while (nodes.size()>1){
            quickSort(nodes);
            //获取权值最小的两个点
            Node left=nodes.get(nodes.size()-1);
            Node right=nodes.get(nodes.size()-2);
            //生成新节点，新节点的权值为两个子节点的权值之和
            Node<Character> parent=new Node<>(null,left.weight+right.weight);
            //让新节点作为左右两个子节点
            parent.left=left;
            parent.right=right;
            //删除权值最小的两个节点
            nodes.remove(nodes.size()-1);
            nodes.remove(nodes.size()-1);
            //将新节点加入到集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static void quickSort(List<Node> nodes) {
        subSort(nodes, 0, nodes.size()-1);
    }

    /**
     * 实现快速排序算法，用于对节点进行排序，实现方法：指针交换法
     * @param nodes
     * @param start
     * @param end
     */
    private static void subSort(List<Node> nodes, int start, int end) {
        if(start<end){
            Node base=nodes.get(start);
            // i从左边搜索，搜索大于分界值的元素的索引
            int i = start+1;
            // j从右边开始搜索，搜索小于分界值的元素的索引
            int j = end;
            while (true){
                // 找到大于分界值的元素的索引，或者i已经到了end处
                while (i<end&&nodes.get(i).weight>=base.weight){
                    i++;
                }
                while (j>start&&nodes.get(j).weight<=base.weight){
                    j--;
                }
                if(i<j){
                    swap(nodes,i,j);
                }else {
                    break;
                }
            }
            swap(nodes,start,j);
            //递归左边子序列
            subSort(nodes, start, j - 1);
            //递归右边子序列
            subSort(nodes, j + 1, end);
        }
    }
    /**
     * 使用队列实现广度优先遍历
     */
    public static List<Node> BFS(Node root){
        Queue<Node> queue=new ArrayDeque<Node>();
        List<Node> list=new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node temp=queue.poll();
            list.add(temp);
            if(temp.left!=null){
                queue.offer(temp.left);
            }
            if(temp.right!=null){
                queue.offer(temp.right);
            }
        }
        return list;
    }

    /**
     * 输出结果
     * Node[data=null, weight=100.0]
     * Node[data=A, weight=40.0]
     * Node[data=null, weight=60.0]
     * Node[data=null, weight=30.0]
     * Node[data=D, weight=30.0]
     * Node[data=C, weight=10.0]
     * Node[data=null, weight=20.0]
     * Node[data=null, weight=10.0]
     * Node[data=E, weight=10.0]
     * Node[data=F, weight=2.0]
     * Node[data=B, weight=8.0]
     * @param args
     */

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();

        nodes.add(new Node("A", 40.0));
        nodes.add(new Node("B", 8.0));
        nodes.add(new Node("C", 10.0));
        nodes.add(new Node("D", 30.0));
        nodes.add(new Node("E", 10.0));
        nodes.add(new Node("F", 2.0));

        Node root = Huffman_Tree.createTree(nodes);
        List<Node> list=BFS(root);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

}
