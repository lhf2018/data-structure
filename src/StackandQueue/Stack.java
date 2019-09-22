package StackandQueue;

/**
 * 本例用链表实现了栈，展示了栈的peek，pop，push的基本操作
 */
public class Stack {
    /**
     * 数据节点
     */
    private class Data{
        private Object object;
        private Data next=null;

        Data(Object object) {
            this.object = object;
        }
    }
    private Data first=null;//栈的头节点
    private int size=0;//栈的长度

    /**
     * 从前面添加数据
     */
    private void insertFirst(Object obj){
        Data data=new Data(obj);
        data.next=first;
        first=data;
        size++;
    }

    /**
     * 删除第一个
     */
    private Object deleteFirst() throws Exception {
        if(first==null){
            throw new Exception("empty!");
        }
        Data temp=first;
        first=first.next;
        size--;
        return temp.object;
    }
    /**
     * 添加元素
     */
    public void push(Object object){
        insertFirst(object);
    }

    /**
     * 弹出元素
     */
    public Object pop() throws Exception {
        return deleteFirst();
    }

    /**
     * 显示最顶层值，不弹出
     */
    public Object peek(){
        return first.object;
    }

    public static void main(String[] args) throws Exception {
        Stack stack=new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("peek: "+stack.peek());
        System.out.println("stack.size: "+stack.size);
        int len=stack.size;
        for(int i=0;i<len;i++){
            System.out.println(stack.pop());
        }
    }

}
