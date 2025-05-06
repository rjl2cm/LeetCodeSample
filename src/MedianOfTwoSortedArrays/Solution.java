package MedianOfTwoSortedArrays;


//给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
//
//        算法的时间复杂度应该为 O(log (m+n)) 。
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {

    public static void main(String[] args) {
        int[] num1 = new int[] {1,3};
        int[] num2 = new int[] {2,7};
        System.out.println(findMedianSortedArrays(num1, num2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num1Start = 0, num2Start = 0;
        int num1End = nums1.length > 0 ? nums1.length - 1 : -1;
        int num2End = nums2.length > 0 ? nums2.length - 1 : -1;
        if (nums1.length == 0 && nums2.length == 0) return 0d;
        for(int i = 0, size = nums1.length + nums2.length; i < (size -1)/2; i++) {
            // 减少一个较小的数字
            if (num1Start < nums1.length && num1Start <= num1End) {
                if (num2Start < nums2.length && num2Start <= num2End) {
                    // 1，2均有效
                    if (nums1[num1Start] <= nums2[num2Start]) {
                        num1Start++;
                    } else {
                        num2Start++;
                    }
                } else {
                    // 1有效，2无效
                    num1Start++;
                }
            } else {
                // num1Start无效, 需要铁定走2
                num2Start++;
            }
            // 减少一个较大的数字
            if (num1End >= 0 && num1End >= num1Start) {
                if (num2End >= 0 && num2End >= num2Start) {
                    // 1,2均有效
                    if (nums1[num1End] >= nums2[num2End]) {
                        num1End--;
                    } else {
                        num2End--;
                    }
                } else {
                    // 1有效,2无效
                    num1End--;
                }
            } else {
                // num1End无效，铁定走2
                num2End--;
            }
        }
        double sum = 0;
        int count = 0;
        if (num1Start < num1End) {
            sum += nums1[num1Start];
            sum += nums1[num1End];
            count += 2;
        } else if (num1Start == num1End) {
            sum += nums1[num1Start];
            count += 1;
        }
        if (num2Start < num2End) {
            sum += nums2[num2Start];
            sum += nums2[num2End];
            count += 2;
        } else if (num2Start == num2End) {
            sum += nums2[num2Start];
            count += 1;
        }
        return sum/count;
    }
}