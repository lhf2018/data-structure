package find;

public class FibonacciSearch {
    /**
     * 构造一个斐波那契数列
     * @param n
     * @return
     */
    int[] createfibonacci(int n){
        int[] res=new int[n];
        res[0]=1;
        res[1]=1;
        for(int i=2;i<n;i++){
            res[i]=res[i-1]+res[i-2];
        }
        return res;
    }

    /**
     *
     * @param array 要查找的数组
     * @param key 要查找的数字
     * @return 索引
     */
    public int search(int[] array,int key){
        int low=0;
        int high=array.length-1;
        int mid=0;

        int[] F=createfibonacci(array.length);
        //计算n位于斐波那契数列的位置k
        int k=0;
        //超过数组长度最近数组长度的那个数
        while (array.length>F[k]-1){
            k++;
        }
        // 创建临时数组
        int[] temp=new int[F[k]-1];
        for(int i=0;i<array.length;i++){
            temp[i]=array[i];
        }
        // 序列补充至F[k]个元素
        // 补充的元素值为最后一个元素的值
        for (int i = array.length; i < F[k] - 1; i++) {
            temp[i] = temp[high];
        }
        while (low<=high){
            mid=low+F[k-1]-1;
            if(temp[mid]>key){
                high=mid-1;
                k--;
            }else if(temp[mid]<key){
                low=mid+1;
                k-=2;
            }else{
                // 如果为真则找到相应的位置
                if (mid <= high) {
                    return mid;
                }else {
                    // 出现这种情况是查找到补充的元素
                    // 而补充的元素与high位置的元素一样
                    return high;
                }
            }
        }
        return -1;

    }
    public static void main(String[] args) {

        int[] array = { 1, 5, 15, 22, 25, 31, 39, 42, 47, 49, 59, 68, 88 };
        FibonacciSearch search=new FibonacciSearch();
        System.out.println(search.search(array,49));
    }
}
