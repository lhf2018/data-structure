package LinearList;

/**
 * 本例用数组实现静态链表，每个节点使用Element对象，对象包含本节点值和下一节点位置
 */
public class StaticLinkList {
    private Element[] linklist=null;
    private int default_size=5;//默认存储大小
    private int currentFree=0;//指向当前空闲位置
    private int size=0; //统计已添加节点数量，用来计算是否扩容
    private int head=0; //指向头部节点，头部节点并不一定是数组第0个

    /**
     * 静态链表的节点
     */
    class Element{
        int data;
        int cur;
    }

    /**
     * 初始化链表
     */
    public StaticLinkList() {
        linklist=new Element[default_size];
        for(int i=0;i<linklist.length;i++){
            linklist[i]=new Element();
            linklist[i].data=-1;
            linklist[i].cur=i+1;
        }
        //头节点也存储数据
        currentFree = 0;
    }
    /**
     * 给链表添加数据，每当链表满了就给链表添加额外的空间
     */
    public void add(int data){
        if(size<linklist.length){
            linklist[currentFree].data=data;
            currentFree=linklist[currentFree].cur;
            size++;
        }else {
            addLinkSpace(); //扩容
            linklist[currentFree].data=data;
            currentFree=linklist[currentFree].cur;
            size++;
        }
    }
    /**
     * 增加链表空间
     */
    private void addLinkSpace() {
        default_size+=8;
        Element[] link=linklist;
        linklist=new Element[default_size];
        System.arraycopy(link,0,linklist,0,link.length);
        for(int i=link.length;i<default_size;i++){
            linklist[i]=new Element();
            linklist[i].data=-1;
            linklist[i].cur=i+1;
        }
        currentFree=link.length;
    }
    /**
     * 删除指定位置的节点,第index个节点，index包含0
     */
    public void delete(int index){
        if(index<0||index>size){
            System.out.println("超出链表长度");
        }else if(index==size-1){
            size--;
        }else if(index==0) {
            int temp=head;
            head=linklist[temp].cur;
            int j=head;
            while (currentFree!=linklist[j].cur){
                j=linklist[j].cur;
            }
            int next=linklist[j].cur;
            linklist[j].cur=temp;
            linklist[temp].cur=next;
            currentFree=temp;
            size--;
        }else {
                int i=head;
                while (index!=linklist[i].cur){
                    i=linklist[i].cur;
                }
                int j=head;
                while (currentFree!=linklist[j].cur){
                    j=linklist[j].cur;
                }
                linklist[i].cur=linklist[index].cur;
                linklist[j].cur=index;
                linklist[index].cur=currentFree;
                currentFree=index;
                size--;
            }
        }

    /**
     * 打印静态链表的值
     */
    public void printlist(){
        int temp=head;
        while (linklist[temp].cur!=currentFree){
            System.out.print(linklist[temp].data+"-->");
            temp=linklist[temp].cur;
        }
    }

    public static void main(String[] args) {
        StaticLinkList linkList=new StaticLinkList();
        int[] nums=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
        for(int num:nums){
            linkList.add(num);
        }
        System.out.println("linkLength:" + linkList.size);
        linkList.printlist();
        System.out.println();
        linkList.delete(2);
        System.out.println("linkLength:" + linkList.size);
        linkList.printlist();
        System.out.println();
        linkList.delete(4);
        System.out.println("linkLength:" + linkList.size);
        linkList.printlist();
        System.out.println();
        linkList.add(13);
        System.out.println("linkLength:" + linkList.size);
        linkList.printlist();
    }

}
