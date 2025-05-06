package LongestValidParentheses;

import java.util.Stack;

/**
 *  给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *  https://leetcode.cn/problems/longest-valid-parentheses/
 */
class Solution {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("()(()"));
    }

    public static int longestValidParentheses(String s) {
        Stack<Object> stack = new Stack<>();
        char c;
        int max = 0, segament = 0, temp = 0;

        for (int i = 0, len = s.length(); i < len; i++) {
            c = s.charAt(i);
            if (c == '(') {
                // 应入栈
                stack.push(i);
            } else if (c == ')') {
                // 应出栈
                stack.pop();
            }
        }
        return max;
    }
}