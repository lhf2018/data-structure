package string;

/**
 * 本算法实现字符串查找的kmp算法
 * 可参考博客：https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html
 */
public class KMP {
    public int Kmpsearch(String s,String p){
        /*
        得到字符串p的next数组
         */
        int[] next=getNext(p);

        int i=0;
        int j=0;
        int slen=s.length();
        int plen=p.length();
        while (i<slen&&j<plen){
            if(j==-1||s.charAt(i)==p.charAt(j)){
                i++;
                j++;
            }else {
                j=next[j];
            }
        }
        /*
        如果j的值为plen，那么输出字符串的起始位置，否则输出-1
         */
        if(j==plen){
            return i-j;
        }else {
            return -1;
        }
    }

    public int[] getNext(String p){
        char[] strKey=p.toCharArray();
        int[] next=new int[strKey.length];
        /*
        初始条件
         */
        int j=0;
        int k=-1;
        next[0]=-1;
        /*
         根据已知的前j位推测第j+1位
          */
        while (j<next.length-1){
            if(k==-1||strKey[j]==strKey[k]){
                next[++j]=++k;
            }else{
                k=next[k];
            }
        }
        return next;
    }

    /**
     * 测试结果:15
     */
    public static void main(String[] args) {
        KMP kmp=new KMP();
        System.out.println(kmp.Kmpsearch("BBC ABCDAB ABCDABCDABDE","ABCDABD"));
    }
}
