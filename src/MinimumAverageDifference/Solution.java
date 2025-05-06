package MinimumAverageDifference;

//给你一个下标从 0 开始长度为 n 的整数数组 nums 。
//
//下标 i 处的 平均差 指的是 nums 中 前 i + 1 个元素平均值和 后 n - i - 1 个元素平均值的 绝对差 。两个平均值都需要 向下取整 到最近的整数。
//
//请你返回产生 最小平均差 的下标。如果有多个下标最小平均差相等，请你返回 最小 的一个下标。
//
//注意：
//
//两个数的 绝对差 是两者差的绝对值。
// n 个元素的平均值是 n 个元素之 和 除以（整数除法） n 。
//0 个元素的平均值视为 0 。

// https://leetcode-cn.com/contest/biweekly-contest-77/problems/minimum-average-difference/

class Solution {
    public int minimumAverageDifference(int[] nums) {
        long[] sumNum = new long[nums.length];
        long[] sumNumR = new long[nums.length];
        for(int i = 0, size = nums.length; i < size; i++) {
            int j = size - i - 1;
            if (i == 0){
                sumNum[i] = nums[i];
                sumNumR[j] = nums[j];
                continue;
            }
            sumNum[i] = nums[i] + sumNum[i - 1];
            sumNumR[j] = nums[j] + sumNumR[j + 1];
        }
        int index = -1;
        long min = Long.MAX_VALUE;
        long temp;
        long[] avgDiff = new long[nums.length];
        for(int i = 0, size = nums.length; i < size; i++) {
            if(i == size - 1) {
                temp = sumNum[i] / (i + 1) - 0;
            } else {
                temp = sumNum[i] / (i + 1)  - sumNumR[i + 1] / (size - i - 1);
            }
            if (temp < 0) temp = - temp;
            avgDiff[i] = temp;
            if (min > temp) {
                min = temp;
                index = i;
            }
        }
        return index;
    }
}