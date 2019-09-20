package LinearList;

/**
 * 设计一个动态链表类
 */
public class LinkedList {
    private Node head=null;//头节点
    /**
     * 链表节点
     */
    class Node{
        Node next=null;
        int data;

        Node(int data) {
            this.data = data;
        }
    }
    /**
     * 向链表中插入数据
     */
    public void add(int data){
        Node node=new Node(data);
        if(head==null){
            head=node;
            return;
        }
        Node temp=head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=node;
    }
    /**
     * 返回节点长度
     */
    public int length(){
        int length=0;
        Node temp=head;
        while (temp!=null){
            temp=temp.next;
            length++;
        }
        return length;
    }
    /**
     *删除第index个节点
     */
    public boolean deleteNode(int index){
        if(index<1||index>length()){
            return false;
        }
        if(index==1){
            head=head.next;
            return true;
        }
        int i=1;
        Node preNode=head;
        Node curNode=preNode.next;
        while (curNode!=null){
            if(i==index){
                preNode.next=curNode.next;
                return true;
            }
            preNode=preNode.next;
            curNode=curNode.next;
            i++;
        }
        return false;
    }

    /**
     * 遍历链表输出
     */
    public void printlist(){
        Node temp=head;
        while (temp!=null){
            System.out.print(temp.data+"-->");
            temp=temp.next;
        }
    }
    //测试
    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
        int[] nums=new int[]{4,5,8,18,3,1,48,9,4,6,11,10,7};
        for(int num:nums){
            linkedList.add(num);
        }
        System.out.println("linkLength:" + linkedList.length());
        linkedList.printlist();
        System.out.println();
        linkedList.deleteNode(4);
        System.out.println("linkLength:" + linkedList.length());
        linkedList.printlist();
    }
}
