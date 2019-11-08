package os;

import java.util.HashMap;

/**
 * 本代码实现了内存管理中的页面置换算法LRU
 * 使用hashmap和链表一起实现
 */
public class LRUCachel {
    class Node{
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    private int capacity;
    HashMap<Integer,Node> map=new HashMap<>();
    Node head;
    Node end;
    //初始化大小，缓存是有大小限制的，超过规定的大小时就得移除
    public LRUCachel(int capacity) {
        this.capacity = capacity;
    }
    //获取一个缓存数据之后，应该把这个数据在当前位置中移除，并重新添加到头的位置，这些都是在返回数据之前完成的
    public int get(int key){
        if(map.containsKey(key)){
            Node node=map.get(key);
            remove(node);
            setHead(node);
            return node.value;
        }
        return -1;
    }
    //移除元素分为，N的前边和N的后边都要看是怎么样的情况
    public void remove(Node node){
        if(node.pre!=null){
            node.pre.next=node.next;
        }else {
            head=node.next;
        }
        if(node.next!=null){
            node.next.pre=node.pre;
        }else {
            end=node.pre;
        }
    }
    //放刚刚访问到的放到头部
    public void setHead(Node node){
        node.next=head;
        node.pre=null;
        if(head!=null){
            head.pre=node;
        }
        head=node;
        if(end==null){
            end=head;
        }
    }
    //设置看原位置是否有元素，如果有的话就替换，这证明使用过了，然后将其替换为头结点的元素，若果是一个新的节点就要判断它的大小是否符合规范
    public void set(int key,int value){
        if(map.containsKey(key)){
            Node old =map.get(key);
            old.value=value;
            remove(old);
            setHead(old);
        }else {
            Node created=new Node(key,value);
            if (map.size() > capacity) {
                map.remove(end.key);
                remove(end);
                setHead(created);
            }else {
                setHead(created);
            }
            map.put(key,created);
        }
    }
}
