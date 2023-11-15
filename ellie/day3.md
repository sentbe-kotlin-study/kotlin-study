## 22. 테스트
* 중위표기법 : `a.함수(b)` 를 `a 함수 b` 처럼 쓸 수 있게 해준다.
* infix 키워드를 붙인 함수만 중위 표기법을 사용해 호출할 수 있음
```kotlin
fun main() {
  calculateBMI(160.0, 68.0) eq "Normal weight"
  calculateBMI(100.0, 68.0) eq "Underweight"
  calculateBMI(200.0, 68.0) eq "Overweight"
}

fun calculateBMI(
  lbs: Double,
  height: Double
): String {
  val bmi = lbs / (height * height) * 703.07
  return if (bmi < 18.5) "Underweight"
  else if (bmi < 25) "Normal weight"
  else "Overweight"
}
```
> [바로가기](./example/src/day3/TDDWorks.kt)
----

## 23. 예외
```kotlin
import atomictest.*

fun main() {
    capture {
        "1#$".toInt()
    } eq "NumberFormatException: For input string: \"1#$\""
}
```
* `atomictest` 를 까보면 cature는 실제로 아래와 같은 `try~catch` 문으로 짜여져있음
```kotlin
fun capture(f:() -> Unit): CapturedException =
  try {
    f()
    CapturedException(null,
      "$ERROR_TAG Expected an exception")
  } catch (e: Throwable) {
    CapturedException(e::class,
      (e.message?.let { ": $it" } ?: ""))
  }
```
* 차라리 `try~catch` 문을 연습 하는 것이 어떨지?
```kotlin
fun main() {
    try {
        var a = 1 / 0
    } catch (e: Throwable) {
        println(e.toString())
    }
}
```
> [바로가기](./example/src/day3/TryCatch.kt)

## 24. 리스트
* List 는 코틀린의 순차적 컨테이너 타입
* listOf() 는 읽기 전용 리스트
* mutableListOf() 는 가변 리스트
```kotlin
fun main() {
    val doubles = listOf(2.2, 1.1, 3.3)
    println(doubles.sorted()) // ??
}
```

<details>
<summary>참고용</summary>

[1.1, 2.2, 3.3]
* sort() 는 원본 List 를 바꿈 : 이를 인플레이스(in place) 라고 부른다
* sorted() 는 원본의 원소들을 정렬한 새로운 List 반환
</details>

---

## 25. 가변 인자 목록
* vararg 키워드는 길이가 변할 수 있는 인자 목록
* 함수의 인자 값으로 임의의 타입 Array 를 넘김
* Array 는 항상 가변인자이므로, 인자 목록으로 변환하려면 앞에 **스프레드 연산자(*)** 를 붙여야 함
```kotlin
fun first(vararg numbers: Int) : String {
    var result = ""

    numbers.sortDescending()
    for (i in numbers) {
        result += "[$i]"
    }

    return result
}

fun second(vararg numbers: Int) = first(*numbers)
fun main() {
    println(second(7,9,32)) // ??
}
```
> [바로가기](./example/src/day3/TwoFunctionsWithVarargs.kt)

---

## 26. 집합
* Set은 중복이 존재하지 않는 컬렉션
```kotlin
fun main() {
    val mutableSet = mutableSetOf<Int>()

    try {
        mutableSet += 4
        mutableSet += 3
        mutableSet += 3

        mutableSet -= 2

        mutableSet += 7

        println(mutableSet) // [1]
    } catch (e: Throwable) {
        println(e) // [2]
    }

    println(mutableSet.toList()) // [3]
}
```
> [바로가기](./example/src/day3/MutableSet.kt)
---

## 27. 맵
* 맵은 Key and Value 형식의 데이터 타입이다.
```kotlin
class Contact(
    val name: String,
    val phone: String
) {
    override fun toString(): String {
        return "Contact('$name', '$phone')"
    }
}

fun main() {
    val miffy = Contact("miffiy", "111")
    val cleo = Contact("cleo", "222")

    // TODO : MapOf 를 사용하여 contact 변수를 선언하세요

    // contacts["111"] eq "miffy"
    // contacts["222"] eq "cleo"
}
```
> [바로가기](./example/src/day3/ContactMap.kt)
---

## 28. 프로퍼티 접근자
* private 로 선언한 멤버 변수에 Getter와 Setter를 사용하자
```kotlin
class Counter() {
    var value: Int = 0
        private set

    var value2: Int = 0

    fun inc() = value++
}
fun main() {
    val counter = Counter()
    repeat(10) {
        counter.inc()
    }

    println(counter.value) // [1]
    println(counter.value++) // [2]
    println(counter.value2++) // [3]
}
```
> [바로가기](./example/src/day3/Counter.kt)