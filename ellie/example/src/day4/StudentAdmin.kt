package day4
/*
문제. 학생 관리 시스템
Student라는 data class를 만드세요.

이 클래스는 name (문자열), grade (정수), 그리고 email (nullable 문자열)을 프로퍼티로 가져야 합니다.

Student 객체의 리스트를 만들고, 이 중에서 email이 null이 아닌 학생들의 이름과 이메일을 출력하는 함수를 작성하세요.

email이 null인 경우, "Email not provided"라고 출력하세요.

각 학년별로 "Welcome to [학년] grade, [이름]!" 메시지를 출력하는 함수를 when 표현식을 사용하여 작성하세요.
 */

data class Student(
    val name: String,
    val grade: Int,
    val email: String?
)

fun main() {
    var s1 = Student("Ellie", 3 , null)
    var s2 = Student("Lydia", 2, "lydia@email.com")
    var s3 = Student("Dory", 2, "dory@email.com")
    var s4 = Student("Kai", 4, null)

    var students = listOf<Student>(s1,s2,s3,s4)
    for(s in students) {
        if(s?.email == null) {
            println(s.name + " : Email not provided")
        }
    }

    println(welcomeGrade(s1))
    println(welcomeGrade(s2))
    println(welcomeGrade(s3))
    println(welcomeGrade(s4))

}

fun welcomeGrade(s:Student): String =
    when(s.grade) {
        2 -> "Welcome to 2 grade, "+ s.name +"!"
        3 -> "Welcome to 3 grade, "+ s.name +"!"
        4 -> "Welcome to 4 grade, "+ s.name +"!"
        else -> "Unknown"
    }