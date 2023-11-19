package day3

import atomictest.eq


class Solution {
    fun solution(arr: IntArray, divisor: Int): IntArray {
        var answer = intArrayOf()

        for(value:Int in arr ) {
            if(value % divisor == 0) {
                answer += value
            }
        }

        if(answer.isEmpty()) {
            answer += -1
        }

        answer.sort()

        return answer
    }

}

fun printArr(arr: IntArray) {
    print("[")
    for(value:Int in arr) {
        print("$value, ")
    }
    println("]")
}
fun main() {
    val result = Solution()
    printArr(result.solution(intArrayOf(5, 9, 7, 10), 5))
    printArr(result.solution(intArrayOf(2, 36, 1, 3), 1))
    printArr(result.solution(intArrayOf(3, 2, 6), 10))
}