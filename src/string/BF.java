package string;

/**
 * BF算法：暴风(Brute Force)算法，是普通的模式匹配算法，也就是挨个匹配，时间复杂度为O(M*N)
 */
public class BF {
    public int find(String str,String tag){
        int pos=-1;
        if(tag.length()==0)return pos;
        outer:
        for(int i=0;i+tag.length()<str.length();i++){
            for(int j=0;j<tag.length();j++){
                if(tag.charAt(j)!=str.charAt(i+j)){
                    continue outer;
                }
            }
            pos=i;
        }
        return pos;
    }

    public static void main(String[] args) {
        BF bf=new BF();
        System.out.println(bf.find("asdas", ""));
    }
}
