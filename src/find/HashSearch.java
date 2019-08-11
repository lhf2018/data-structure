package find;

public class HashSearch {
    /****
     * Hash表检索数据--查找
     *
     * @param hash
     * @param hashlength
     * @param key
     * @return
     */
    public int search(int[] hash,int hashlength,int key){
        int hashAddress=key%hashlength;
        while (hash[hashAddress]!=0&&hash[hashAddress]!=key){
            hashAddress=(++hashAddress)%hashlength;
        }
        // 查找到了开放单元，表示查找失败
        if (hash[hashAddress] == 0)
            return -1;
        return hashAddress;
    }
    /**
     * 在hash数组中插入数据
     */
    public void insertHash(int[] hash,int hashlength,int key){
        //哈希函数
        int hashAddress=key%hashlength;
        while (hash[hashAddress]!=0){
            hashAddress=(++hashAddress)%hashlength;
        }
        hash[hashAddress]=key;
    }

    public static void main(String[] args) {
        int hashLength = 13;

        int [] array  = { 13, 29, 27, 28, 26, 30, 38 };

        //哈希表长度
        int[] hash = new int[hashLength];
        HashSearch hashSearch=new HashSearch();
        for(int i=0;i<array.length;i++){
            hashSearch.insertHash(hash,hashLength,array[i]);
        }
        //返回数组在hash表中的位置
        System.out.println(hashSearch.search(hash,hashLength,28));
    }
}
