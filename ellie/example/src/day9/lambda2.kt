package day9;// 다음 코드를 설명해보세요 -> 논술형

class A {
    fun af() = 1
}

class B {
    fun bf() = 2
}

fun f1(lambda: (A, B) -> Int) = 
    lambda(A(), B())


fun f2(lambda: A.(B) -> Int) =
    A().lambda(B()) 

fun main() {
    println(f1 { aa, bb -> aa.af() + bb.bf() })
    println(f2 { af() + it.bf()})
}