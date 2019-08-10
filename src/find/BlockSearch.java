package find;

public class BlockSearch {
    /**
     * 分块查找
     * @param index 索引数组
     * @param array 要查找的数组
     * @param key 要查找的数
     * @param m 每块的大小
     */
    public int blocksearch(int[] index,int[] array,int key,int m){
        int i=ordersearch(index,key);
        System.out.println("在第"+i+"块");
        if(i>=0){
            int start=m*i;
            int end=(i+1)*m;
            for(int j=start;j<end;j++){
                if(array[j]==key){
                    return j;
                }
            }
        }
        return -1;
    }

    /**
     * 该函数返回key在第几块
     */
    private int ordersearch(int[] index, int key) {
        if(index[0]>key){
            return 0;
        }
        for(int i=1;i<index.length;i++){
            if(index[i-1]<key&&index[i]>=key){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BlockSearch search=new BlockSearch();
        int[] index={22,48,86};
        int[] array={22, 12, 13, 8, 9, 20, 33, 42, 44, 38, 24, 48, 60, 58, 74, 49, 86, 53};
        System.out.println(search.blocksearch(index,array,53,6));
    }
}
