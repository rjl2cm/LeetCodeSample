package regularexpressionmatching;

/**
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 *
 * 示例 1：
 *
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例3：
 *
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 
 *
 * 提示：
 *
 * 1 <= s.length<= 20
 * 1 <= p.length<= 30
 * s只包含从a-z的小写字母。
 * p只包含从a-z的小写字母，以及字符.和*。
 * 保证每次出现字符* 时，前面都匹配到有效的字符
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 */
class Solution1 {

    public static void main(String[] args) throws Exception {
        Solution1 solution = new Solution1();
        solution.isMatch("", "");
    }

    public boolean isMatch(String s, String p) throws Exception {
        int indexS = 0, indexP = 0;
        char tempStack;
        while(indexP <= p.length() -1 && indexS <= s.length() -1) {
            char charP = p.charAt(indexP);
            char charS = s.charAt(indexS);
            if (charP == '.') {
                //匹配任意字符
                indexP++;
                indexS++;
                // 暂存charS
                tempStack = charS;
                continue;
            } else if (charP == '*') {
//                //重复前面字符
//                if () {
//
//                }
            } else if(charP >= 'a' && charP <= 'z') {
                //正常字符
                if (charP == charS) {
                  indexP++;
                  indexS++;
                } else {
                    return false;
                }
            } else {
                throw new Exception("error char：" + charP + "in String p");
            }
        }
        return true;
    }
}
