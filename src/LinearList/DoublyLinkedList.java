package LinearList;

import java.util.NoSuchElementException;

public class DoublyLinkedList {
    /**
     * 定义节点
     */
    class Node{
        int data;
        Node pre=null;
        Node next=null;

        public Node(int data) {
            this.data=data;
        }
    }
    private Node head;//头节点
    private Node tail;//尾节点
    private int size;//链表大小
    public DoublyLinkedList(){
        head=null;
        tail=null;
    }
    /**
     * 向线性链表的表头插入一个元素
     */
    public void addFirst(int data){
        Node temp=head;
        Node node=new Node(data);
        head=node;
        if(temp==null){
            tail=node;
        }else {
            temp.pre=node;
            node.next=temp;
        }
        size++;
    }
    /**
     * 向线性链表的表尾插入一个元素
     */
    public void addTail(int data){
        Node temp=tail;
        Node node=new Node(data);
        tail=node;
        if(temp==null){
            head=node;
        }else {
            temp.next=node;
            node.pre=temp;
        }
        size++;
    }
    /**
     * 向线性链表中的指定位置插入一个元素
     */
    public void add(int data,int index){
        if(index<0||index>size){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        if(index==size){
            addTail(data);
        }else if(index==0){
            addFirst(data);
        }else {
            Node node=head;
            while (index>0){
                node=node.next;
                index--;
            }
            Node temp=new Node(data);
            node.pre.next=temp;
            temp.next=node;
            temp.pre=node.pre;
            node.pre=temp;
            size++;
        }
    }
    /**
     * 移走线性链表的头节点
     */
    public void removeFirst(){
        if(head==null){
            throw new NoSuchElementException("此节点不存在");
        }
        head=head.next;
        if(head!=null){
            head.pre=null;
        }
        size--;
    }
    /**
     * 移走尾节点
     */
    public void removeTail(){
        if(tail==null){
            throw new NoSuchElementException("此节点不存在");
        }
        tail=tail.pre;
        if(tail!=null){
            tail.next=null;
        }
        size--;
    }
    /**
     * 移走线性表中的任意一个节点
     */
    public void remove(int index){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("线性表越界");
        }
        if(index==0){
            removeFirst();
        }else if(index==size-1){
            removeTail();
        }else {
            Node node=head;
            while (index>0){
                node=node.next;
                index--;
            }
            node.pre.next=node.next;
            node.next.pre=node.pre;
        }
        size--;
    }


    /**
     * 正序打印链表
     */
    public void print1(){
        Node temp=head;
        while (temp!=null){
            System.out.print(temp.data+"-->");
            temp=temp.next;
        }
    }

    /**
     * 逆序打印链表
     */
    public void print2(){
        Node temp=tail;
        while (temp!=null){
            System.out.print(temp.data+"-->");
            temp=temp.pre;
        }
    }

    /**
     * 查找链式线性表中指定元素的索引
     */
    public int locate(int element){
        Node current = head;
        for(int i=0;i<size && current != null;i++, current = current.next){
            if(current.data==element){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        DoublyLinkedList linkedList=new DoublyLinkedList();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.add(4,2);
//        linkedList.removeFirst();
//        linkedList.removeTail();
//        linkedList.remove(2);
        linkedList.print1();
        System.out.println();
        linkedList.print2();

    }
}
