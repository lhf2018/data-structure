package stackqueue;

/**
 * 本例用链表实现了一个队列，并实现了push、peek、pop基本操作
 */
public class Queue {
    /**
     * 节点值
     */
    class Node{
        private int data;
        private Node next;

        Node(int data) {
            this.data = data;
        }

        int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
    private Node frontNode; //首节点
    private Node rearNode;  //尾节点
    private int size;
    public Queue(){
        this.frontNode=null;
        this.rearNode=null;
        this.size=0;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty(){
        return frontNode==null;
    }
    public void push(int data){
        Node newnode=new Node(data);
        //位指针不为空，设置下一位
        if(rearNode!=null){
            rearNode.next=newnode;
        }
        rearNode=newnode;
        //首为空，意味着刚刚设置了一个尾节点
        if(frontNode==null){
            frontNode=rearNode;
        }
        size++;
    }

    /**
     * 弹出队列
     */
    public int pop() throws Exception {
       int data=0;
       if(frontNode!=null){
           data=frontNode.getData();
           frontNode=frontNode.next;
           size--;
       }else {
           throw new Exception("Queue Empty");
       }
       return data;
    }

    /**
     * 查看首端的值
     */
    public int peek() throws Exception {
        if(frontNode!=null){
            return frontNode.getData();
        }else {
            throw new Exception("Queue Empty");
        }
    }

    public static void main(String[] args) throws Exception {
        Queue queue=new Queue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        int len=queue.size;
        for(int i=0;i<len;i++){
            System.out.println(queue.pop());
        }
    }

}
