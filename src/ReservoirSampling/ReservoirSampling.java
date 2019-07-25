package ReservoirSampling;

/**
 * 实现蓄水池抽样算法
 */

/**
 * 用蓄水池算法模拟从10个数中随机抽取一个数
 */
public class ReservoirSampling {

    public int reservoir(){
        int[] raw_list=new int[]{0,1,2,3,4,5,6,7,8,9};//定义10个元素
        int res=raw_list[0];//蓄水池初始化
        for(int i=1;i<10;i++){//从第k+1个元素开始遍历
            if(Math.random()<1.0/(i+1)){
                res=raw_list[i];//蓄水池里的元素替换
            }
        }
        return res;
    }

    /**
     * 测试结果：
     * 0出现的次数:10072
     * 1出现的次数:9990
     * 2出现的次数:10019
     * 3出现的次数:9823
     * 4出现的次数:10106
     * 5出现的次数:9991
     * 6出现的次数:9937
     * 7出现的次数:10094
     * 8出现的次数:9951
     * 9出现的次数:10017
     */
    public static void main(String[] args) {
        ReservoirSampling sampling=new ReservoirSampling();
        int[] num=new int[10];
        for(int i=0;i<100000;i++){
            int res=sampling.reservoir();
            num[res]++;
        }
        for(int i=0;i<num.length;i++){
            System.out.println(i+"出现的次数:"+num[i]);
        }
    }
}
