## 11. while 로 반복하기

* while문 : false 이면 절대 실행 안함
```kotlin
while (true) {
    // 반복 코드
}
```

* do-while문 : 조건이 false 여도 최소 한 번은 실행
```kotlin
do {
    // 반복 코드
} while (true)
```
---
## 12. 루프와 범위
* for 루프
```kotlin
for ( v in 값들=arrays or collection or list ...) {
    // v 를 사용함
}
```
> 예시 : [forLoop.kt](./example/src/chapter2/forLoop.kt)

* 범위 표현식
```kotlin
val range1 = 1..10 // 1부터 10까지 ( 1 ~ 10 )
val range2 = 0 until 100 // 0부터 100 이전까지 ( 0 ~ 99 )
val range3 = 5 downTo 0 // 5 4 3 2 1 0
val range4 = 10 downTo 1 step 3 // 10 7 4 1
val range5 = 'a'..'z' // a ~ z
```
> 참고 : 문자열은 0부터 index 시작함.
* Char 연산
```kotlin
val char : Char = 'a'
println(char + 1 ) // b
```
> 예시 : [charPlus.kt](./example/src/chapter2/charPlus.kt)
---
## 13. in 키워드
어떤 값이 주어진 값 안에 포함되는지 검사한다.
```kotlin
val range1 = 1..10
println(3 in range1) // true
println(100 in range1) // false

// 문자열 포함 여부도 확인 가능!
val range2 = 'aa'..'zz'
println('ab' in range2) // true
println('za' in range2) // false
```
---
## 14. 식과 문
* 식은 값을 만든다
* 문은 상태를 변경한다. = 부수적인 효과를 내기 위해 사용 (ex. for문)
---

## 15. 요약파트 넘김

---
## 16. 객체는 모든 곳에 존재한다
* 코틀린은 하이브리드 객체-함수형 언어이다.
* 
```kotlin
    val list = listOf(1,4,8)
    list[0] eq 1
    list.reversed() eq listOf(8,4,1)
    list.sorted() eq listOf(1,4,8)
    list.sum() eq 13
```
> 예시 : [ListCollection.kt](./example/src/chapter2/ListCollection.kt)
---
## 17. 객체 만들기
```kotlin
class A // 이름만 있는 클래스 선언
val a = A()

class Cat { // 멤버 함수를 가진 클래스 선언
    fun meow() = "mmmmmwww!"
}

fun main() {
    val cat = Cat()
    cat.meow() // mmmmmwww!
}
```

## 18. 프로퍼티
* 기억을 되살려서, var 는 가변 / val 은 불변
* 클래스의 인스턴스를 생성하면 인스턴스들은 각각의 메모리를 따로 할당 받는다.

## 19. 생성자
* 객체 생성 시에 객체를 초기화 하는 특별한 멤버 함수
```kotlin
class Scientist(val name: String) {
    override fun toString() : String {
        return "Scientist('$name')"
    }
}

fun main() {
    val ellie = Scientist("Ellie")
    println(ellie)
}
```
> 실행 : [Scientist.kt](./example/src/chapter2/Scientist.kt)

## 20. 가시성 제한하기
* 변화 되어야 하는 요소와 유지되어야 하는 요소 분리!
* public , private, protected, internal
* private : 선언된 파일 내부에서만 사용 가능
* 한 객체에 대해 참조를 여러 개 유지하는 경우를 에일리어싱(aliasing) 이라고 함 (126p)

## 21. 모듈
* 패키지를 import 해서 반복되는 구문을 줄이자.
```kotlin
import kotlin.math.* // 코틀린 기본 패키지

fun main() {
    println(E) // https://byjus.com/maths/value-of-e/
    println(E.roundToInt())
    println(E.toInt())
}
```
* E value 수학에서 쓰는 값이라 합니다 PI 처럼 상수..
> 실행 : [MathTest.kt](./example/src/chapter2/MathTest.kt)