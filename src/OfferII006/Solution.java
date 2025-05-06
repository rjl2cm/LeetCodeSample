package OfferII006;

import java.util.HashMap;

/**
 给定一个已按照 升序排列的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。

 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0开始计数 ，所以答案数组应当满足 0<= answer[0] < answer[1] <numbers.length。

 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/kLl5u1
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[] {-1, -1};
        }
        int pre = 0, rear = numbers.length-1;
        while(pre <= rear) {
            if (numbers[pre] + numbers[rear] == target) {
                return new int[]{pre, rear};
            } else if (numbers[pre] + numbers[rear] < target) {
                // 小了
                pre++;
            } else  {
                // 大了
                rear--;
            }
        }
        return new int[] {-1, -1};
    }
}

/**
 *  解法二，可应对无序数组
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Solution2 {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) {
            return new int[] {-1, -1};
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0, len = numbers.length; i < len; i++) {
            int x = target - numbers[i];
            if (map.containsKey(x)) {
                return new int[] {i, map.get(x)};
            } else {
                map.put(numbers[i], i);
            }
        }
        return new int[] {-1, -1};
    }
}