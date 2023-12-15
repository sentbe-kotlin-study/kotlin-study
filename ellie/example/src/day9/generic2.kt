package day9
import atomictest.eq

/*
제네릭 타입을 인수로 받는 Items라는 인터페이스 함수를 만드세요

Items 인터페이스는 next() 라는 함수를 가집니다.

이 함수는 null 을 리턴할 수 있습니다.

이 함수는 제네릭 타입을 리턴 타입으로 가집니다

itemIter 함수는 (정해지지 않은 갯수의) 여러개의 제네릭 타입을 생성자로 받는 함수 입니다.

itemIter 함수는 또한 Items 인터페이스를 상속합니다.

itemIter 함수는 Items<T> 객체를 리턴값 으로 가집니다.

next()를 호출할 때마다 현재 element 가 생성되고 인덱스가 증가합니다.

더 이상 항목이 없으면 next()는 null을 반환합니다.

itemIter 함수 내부에 index 변수를 선언하면 편하게 문제를 풀 수 있습니다.
* */

// TODO
fun interface Items<T> {
    fun next() : T?
}

fun <T> itemIter(vararg args: T) : Items<T> {
    var index = 0
    return Items {
        if(index >= args.size) null
        else args[index++]
    }
}


fun main() {

    val s = itemIter("A", "B", "C")
    (0..3).map { s.next() } eq "[A, B, C, null]"
    val i = itemIter(1, 2, 3, 4, 5, 6, 7)
    (0..10).mapNotNull { i.next() } eq
            "[1, 2, 3, 4, 5, 6, 7]"

}