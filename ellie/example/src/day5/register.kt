package day5

data class Student(val id: Int, val name: String)

fun registerStudents(names: List<String>, startId: Int = 0): List<Student> =
    names.mapIndexed{ index, element ->
        Student(index+startId, element)
    }

fun main() {
    val students = listOf("Alice", "Bob")
    println(registerStudents(students)) // listOf(Student(0, "Alice"), Student(1, "Bob"))
    println(registerStudents(students, startId = 10)) // listOf(Student(10, "Alice"), Student(11, "Bob"))
}