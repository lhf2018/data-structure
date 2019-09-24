package LinearList;

/**
 * 数据结构中最基本的一个结构就是线性结构，而线性结构又分为连续存储结构和离散存储结构。所谓的连续存储结构其实就是数组。
 * 主要是数组的基本操作
 * leetcode 88. Merge Sorted Array
 */
public class Array {
    /**
     * 题目：
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     */

    /**
     * 解法思路：
     * 最直接的算法实现是将指针p1 置为 nums1的开头， p2为 nums2的开头，在每一步将最小值放入输出数组中。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n){
        int[] num1_copy=nums1.clone();
        int p1=0;
        int p2=0;

        int p=0;
        while (p1<m&&p2<n){
            if(nums1[p1]>nums2[p2]){
                nums1[p++]=nums2[p2++];
            }else{
                nums1[p++]=num1_copy[p1++];
            }
        }
        if(p1<m){
            System.arraycopy(num1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if(p2<n){
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

    /*
    测试结果 1 2 2 3 5 6
     */
    public static void main(String[] args) {
        Array array=new Array();
        int[] nums1=new int[]{1,2,3,0,0,0};
        int[] nums2=new int[]{2,5,6};
        int m=3;
        int n=3;
        array.merge(nums1,m,nums2,n);
        for(int i=0;i<nums1.length;i++){
            System.out.print(nums1[i]+" ");
        }

    }
}
