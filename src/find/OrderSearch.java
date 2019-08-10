package find;

/**
 * 顺序查找
 */
public class OrderSearch {
    /**
     *
     * @param array 要在这个数组中查找
     * @param find 要查找的数字
     * @return 返回索引,如果没有，返回-1
     */
    public int search(int[] array,int find){
        for(int i=0;i<array.length;i++){
            if(find==array[i]){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] TestData = { 34, 35, 26, 89, 56 };
        OrderSearch orderSearch=new OrderSearch();
        System.out.println(orderSearch.search(TestData,89));
    }
}
