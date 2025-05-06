
public class Solution {

    public static void main(String[] args) {
        int[] ints = new int[] {-1, -2, -3};
//        int[] ints = new int[] {-9,-6,-7,-4,3,8,0,-1,0,6,-8,3,0,2};
//        int[] ints = new int[] {-8,2,2,-6,2,-7,-7,7,1,8,1,-8};
//        int[] ints = new int[] {-2,0,-3,4,-2,2,2,-5,4};
        System.out.println(new Solution().maxSubArray(ints));
    }

    /**
     * 
     * @param A int整型一维数组 
     * @return int整型
     */
    public int maxSubArray (int[] A) {
        int totalSum = Integer.MIN_VALUE, tempSum=0;
        for(int i=0,size = A.length; i < size; i++) {
            tempSum += A[i];
            if(tempSum > totalSum) totalSum = tempSum;
            // 前段内容小于0，直接丢弃
            if(tempSum < 0) tempSum = 0;
        }
        return totalSum;
    }
}