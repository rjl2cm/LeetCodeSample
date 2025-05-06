// https://leetcode.cn/problems/4sum/description/

fun main() {
    println(Solution().fourSum(intArrayOf(-5,5,4,-3,0,0,4,-2), 4))
}

class Solution {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val list = mutableListOf<List<Int>>()
        val tmp = mutableListOf<Int>()
        treeVisit(nums, 0, target, tmp, list)
        return list
    }

    fun treeVisit(nums: IntArray, index: Int, target: Int, tmp: MutableList<Int>, result: MutableList<List<Int>>) {
        // 如果个数已经满足4个，则需要进行判断
        if(tmp.size == 4 && tmp.reduce { acc, i -> acc + i } == target) {
            tmp.toList().run {
                if (!result.contains(this)) {
                    result.add(this)
                }
            }
            return
        }

        if(index < nums.size) {
            // 取之
            tmp.add(nums[index])
            treeVisit(nums, index+1, target, tmp, result)
            tmp.removeLast()

            // 不取
            treeVisit(nums, index+1, target, tmp, result)
        }
    }
}