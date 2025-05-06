import java.io.IO.println

fun main() {
    println(threeSumClosest(intArrayOf(-1, 2, 1, -4) , 1))
}

fun threeSumClosest(nums: IntArray, target: Int): Int {
    var min = Integer.MAX_VALUE
    var result = 0
    var index1 = 0
    var index2 = 0
    var index3 = 0
    while(index1 < nums.size -2) {
        index2 = index1 + 1
        while (index2 < nums.size - 1) {
            index3 = index2 + 1
            while (index3 < nums.size) {
                val sum = nums[index1] + nums[index2] + nums[index3]
                if(Math.abs(sum - target) < min) {
                    min = Math.abs(sum - target)
                    result = sum
                }
                index3++
            }
            index2++
        }
        index1++
    }
    return result
}