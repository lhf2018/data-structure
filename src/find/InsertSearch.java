package find;

/**
 * 插入查找
 * 依据公式：mid=low+(key-a[low])/(a[high]-a[low])*(high-low)
 */
public class InsertSearch {
    public int search(int[] array,int find){
        int low=0;
        int high=array.length-1;
        while (low<high)
        {
            int mid=low+((find-array[low])/(array[high]-array[low]))*(high-low)   ;
            if(find<array[mid]){
                high=mid-1;
            }else if(find>array[mid]){
                low=mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        InsertSearch search=new InsertSearch();
        System.out.println(search.search(array,6));
    }
}
