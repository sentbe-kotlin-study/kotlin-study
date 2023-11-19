package day3
fun main() {
    fun solution(rsp: String): String {
        var answer: String = ""
        var map = mapOf(
            "2" to "0",
            "0" to "5",
            "5" to "2"
        )
        for(ch:Char in rsp) {
            answer += map[ch.toString()]
        }
        return answer
    }

    println(solution("2"))
    println(solution("205"))
}