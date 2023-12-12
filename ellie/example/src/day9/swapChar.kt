package day9

fun String.swapChar(
    // TODO 인자1 : 인덱스 1,
    index1: Int,
    // TODO 인자2 : 인덱스 2,
    index2: Int,
    // TODO 인자3 : Char 을 받아 Boolean 을 리턴하는 함수
    filter: (Char) -> Boolean
): String {
    val sb = StringBuilder(this)
    // 인덱스 1,2의 value가 특정 조건을 만족하면(true 이면) 을 스왚 해주는 로직
    if (filter(sb[index1]) && filter(sb[index2])) {
        sb[index1] = this[index2]
        sb[index2] = this[index1]
    }
    return sb.toString()
}

fun main() {
    val str = "APPLE"
    println(str.swapChar(0, 2) { it in 'A'..'P' })     // TOBE : PPALE
}