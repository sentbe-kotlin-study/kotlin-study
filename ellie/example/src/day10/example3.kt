package day10

// 연산자 오버로딩, 클래스 생성, 평균 및 합계 계산 사용
class StudentGrade(val name: String, val scores: List<Int>) {
    // TODO: + 연산자 오버로딩을 구현하세요. (학생 점수 합산)
    operator fun plus(other: StudentGrade) :StudentGrade {
        return StudentGrade("sum",listOf(scores.sum() + other.scores.sum()))
    }
    // TODO: 과목 점수의 평균 계산을 위한 함수를 구현하세요.
    fun calculateAverage() : Int {
        return scores.sum() / scores.size
    }

    // TODO: 과목 점수의 합계 계산을 위한 함수를 구현하세요.
    fun calculateTotal() : Int {
        return scores.sum()
    }

}

fun main() {

    // Example usage:
    val student1 = StudentGrade("Alice", listOf(85, 90, 78))
    val student2 = StudentGrade("Bob", listOf(75, 88, 92))

    val combinedStudent = student1 + student2 // 두 학생의 점수 합산
    val average = combinedStudent.calculateAverage() // 평균 계산
    val total = combinedStudent.calculateTotal() // 합계 계산
    println("이름: ${combinedStudent.name}, 평균: $average, 합계: $total")
}
