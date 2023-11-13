#### 챕터1. 프로그래밍 베이직

## 11. while로 반복하기
```kotlin
while(불리언 식) {
    // 반복할 코드
}
```
- 모든 산술 연산에 대해 더 짧은 연산자로 대입문을 수행할 수 있는 복합 대입 연산자
  - +=, -=, *=, /=, %=, ++, -- 지원
```kotlin
fun testCondition(i: Int) = i < 100
fun main() {
    var i = 0
    while (testCondition(i)) {
        print(".")
        i += 10
    }
}
// ..........
```
- while과 do 를 함께 쓸 수 있다.
```kotlin
do {
    // 반복할 코드
} while (불리언 식)
```

```kotlin
fun main() {
    var i = 0
    do {
        print(".")
        i += 10
    } while (i < 100)
}
// ..........
```


***


## 12. 루프와 범위
- 코틀린에서 for를 사용하면 범위나 String과 같이 이터레이션 가능한 객체로부터 원소를 직접 얻으면서 루프를 돌 수 있다.
```kotlin
fun main() {
    for (c in "Kotlin") { // String 은 Iterable 이다.
        print("$c ")
        // c += 1 // Error
        // val cannot be reassigned
    }
}
// k o t l i n
```
- c를 명시적으로 var 나 val 로 정의할 수는 없다.
  - 코틀린은 이 변수를 자동으로 val 로 정의하고 타입을 Char 로 추론한다.
- 범위를 사용하면 정숫값을 이터레이션 할 수 있다.
  - .. 으로 범위를 만들면 양 끝의 값을 포함한 범위가 생긴다.
  - 반면 until 로 범위를 만들면 오른쪽 끝 값을 제외한 범위가 생긴다.
    - 1 until 10 은 1..9 와 같다.
  - step 을 사용하면 1..21 step 3 처럼 증가값을 지정할 수 있다.
```kotlin
fun main() {
    for (i in 1..10) {
        print("$i ")
    }
}
// 1 2 3 4 5 6 7 8 9 10
```


***


## 13. in 키워드
- for 루프의 이터레이션에 쓴 것과 같은 in 키워드를 사용해 어떤 값이 범위에 속하는 지를 검사할 수 있다.
  - 반면 !in 은 대상 값이 범위 안에 속해있지 않은 경우 true 를 반환한다.
```kotlin
fun main() {
    val oneToTen = 1..10
    println(5 in oneToTen) // true
    println(11 in oneToTen) // false
    println(0 in oneToTen) // false
    println(-1 in oneToTen) // false
    println(5 !in oneToTen) // false
    println(11 !in oneToTen) // true
    println(0 !in oneToTen) // true
    println(-1 !in oneToTen) // true    
}
```