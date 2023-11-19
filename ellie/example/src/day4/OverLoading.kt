package day4

class OverLoading {
    fun f() = 0
    fun f(n:Int) = n + 2
}

fun main() {
    val o = OverLoading()
    println(o.f()) // ??
    println(o.f(2)) // ??
}