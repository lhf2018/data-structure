package recursion;

/**
 * 汉诺塔
 * n代表n层，A代表第一根，B代表第二根，C代表第三根
 */
public class Hanoi {
    public static void dotower(int n,char A, char B, char C){
        if(n==1){
            System.out.println("盘子 1 从 "+ A + " 移动到 " + C);
        }else{
            //让上面n-1一个移动到第二根杆
            dotower(n-1,A, C, B);
            //移动最下面的到第三根杆
            System.out.println("盘子 "+n+" 从 "+ A + " 移动到 " + C);
            //让第二根杆的n-1个移动到第三根
            dotower(n-1,B,A,C);
        }

    }
    public static void main(String[] args) {
        dotower(3, 'A', 'B','C' );
    }
}
