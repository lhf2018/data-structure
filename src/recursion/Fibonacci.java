package recursion;

public class Fibonacci {
    public int getF(int n){
        if(n<0){
            return -1;
        }else if(n==0){
            return 0;
        }else if(n==1||n==2){
            return 1;
        }else{
            return getF(n-1)+getF(n-2);
        }
    }

    public static void main(String[] args) {
        Fibonacci fibonacci=new Fibonacci();
        for(int i=0;i<20;i++){
            System.out.print(fibonacci.getF(i)+" ");
        }
    }
}
