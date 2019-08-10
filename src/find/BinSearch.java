package find;

/**
 * 二分查找
 */
public class BinSearch {
    /**
     *
     * @param array 要在这个数组中查找,这是一个有序数组
     * @param find 要查找的数字
     * @return 返回索引,如果没有，返回-1
     */
    public int search(int[] array,int find){
        int low=0;
        int high=array.length-1;

        while (low<=high){
            int mid=low+(high-low)/2;
            if(array[mid]==find){
                return mid;
            }else if(array[mid]<find){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        BinSearch search=new BinSearch();
        System.out.println(search.search(array,5));
    }
}
