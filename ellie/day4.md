## 30. 확장 함수
* 코틀린의 `확장 함수`는 기존의 클래스에 멤버 함수를 추가하는 것과 같은 효과를 낸다.
* 확장할 대상 타입은 `수신 객체 타입` 이라고 한다.
* `fun 수신타입.확장함수() {...}` 형식이다.
----

## 31. 이름 붙은 인자와 디폴트 인자
* 함수를 호출하면서 인자의 이름을 지정할 수 있다.
* **Quiz**
```kotlin
class Color (
    val red: Int = 0,
    val green: Int = 0,
    val blue: Int = 0
    ) {
    override fun toString() = "($red, $green, $blue)"
}

fun main() {
    println(Color(red = 77).toString()) // ??
}
```
> [바로가기](./example/src/day4/Color.kt)
----
## 32. 오버로딩
* 파라미터 목록이 여러 다른 함수에 같은 이름을 사용하는 것
```kotlin
class OverLoading {
    fun f() = 0
    fun f(n:Int) = n + 2
}

fun main() {
    val o = OverLoading()
    println(o.f()) // ??
    println(o.f(2)) // ??
}
```
> [바로가기](./example/src/day4/OverLoading.kt)
* 오버로딩이 유용한 이유 : 가치가 있는 단순성을 얻을 수 있고, 더 읽기 좋은 코드를 작성할 수 있음!
----
## 33. When 식
* 자바의 `switch~case문` 비슷한 느낌
* 용법 예시 + **Quiz**
```kotlin
fun mixColor(first: String, second: String) =
    when (setOf(first, second)) { // set 도 들어갈 수 있다 !
        setOf("red", "blue") -> "purple"
        setOf("red", "yellow") -> "orange"
        setOf("blue", "yellow") -> "green"
        else -> "unknown"
    }

fun main() {
    println(mixColor("red", "blue")) // ??
    println(mixColor("blue", "purple")) // ??
}
```
> [바로가기](./example/src/day4/MixColors.kt)
----
## 34. 이넘
* 이넘은 이름을 모아둔 것
* 예시
```kotlin
enum class Level {
    Overflow, High, Medium, Low, Empty
}

fun main() {
    println(Level.Medium)
}
```
> [바로가기](./example/src/day4/Level.kt)

* 이넘은 인스턴스 개수가 미리 정해져있고, 클래스 본문 안에 모든 인스턴스가 나열되어있는 특별한 종류의 클래스
* 멤버 함수나 멤버 변수를 이넘에 정의할 수도 있다 ( **마지막에 세미콜론을 꼭 써줘야 함**)
* Quiz
```kotlin
enum class Direction (val notation: String) {
    North("N"), South("S"), East("E"), West("W");
    val opposite : Direction
        get() = when(this) {
            North -> South
            South -> North
            West -> East
            East -> West
        }
}

fun main() {
    println(Direction.North.notation) // ??
    println(Direction.North.opposite) // ??
    println(Direction.West.opposite.opposite) // ??
    println(Direction.North.opposite.notation) // ??
}
```
----
## 35. 데이터 클래스
* 데이터만 담당하는 클래스가 필요하다면 `data` 키워드를 사용할 수 있다.
* getter, setter, equals(클래스 내부 멤버 변수의 값이 같은지 비교함), toString, copy 를 자동으로 생성해준다.
* 자동으로 구조분해선언을 지원함
* 예시
```kotlin
data class Person(
    val name:String,
    val age:Int
)

fun main() {
    val Ellie = Person("Ellie", 30)
    val Irine = Ellie.copy(name = "Irine") // 객체를 복사할 수 있다, 원하는 인자값을 변경할 수 있음
    val EllieCopy = Ellie.copy()

    println(Ellie.equals(Irine))
    println(Ellie.equals(EllieCopy))
}
```
----
## 36. 구조 분해 선언
* `구조 분해 선언`을 사용하면 여러 식별자를 동시에 선언하면서 초기화 할 수도 있다.
```kotlin
val (a,b,c) = 여러_값이_들어있는_값
```
* 구조 분해 선언은 지역 var나 val 에만 사용할 수 있으며, 클래스 프로퍼티를 정의할 때는 사용할 수 없다.
* Quiz
```kotlin
data class Tuple (
    val i : Int,
    val d: Double,
    val s: String,
    val b: Boolean,
    val l: List<Int>
)

fun main() {
    val tuple = Tuple(
        1, 3.14, "Mouse", false, listOf()
    )
    
    val (i, d, s, b, l) = tuple
    
    println(i)
    println(d)
    println(s)
    println(b)
    println(l)
    
    val (_,_,animal) = tuple
    println(animal)
}
```
> [바로가기](./example/src/day4/Tuple.kt)
----
## 37. 널이 될 수 있는 타입
* 코틀린은 기본적으로 모든 타입에 대해 null을 허용하지 않는다 (non-nullable)
* 하지만 꼭 null이 필요하다면, 타입 이름 되에 물음표(?) 를 붙여서 결과가 null이 될 수도 있음을 표시한다.
* Map은 없는 index를 참조하면 null을 가져온다. Map 의 `[](각괄호)` 연산의 기저 구현인 자바 코드가 null을 반환하기 때문.
* Quiz
```kotlin
fun main() {
    val s1: String = "abc"
    val s2: String? = s1
    
    println(s1.length) // ??
    println(s2.length) // ??
}
```
> [바로가기](./example/src/day4/Dereference.kt)
----
## 38. 안전한 호출과 엘비스 연산자
* 코틀린은 널 가능성을 편리하게 처리할 수 있도록 여러 연산자를 제공한다.
* `안전한 호출`은 일반 호출에 사용하는 점(.) 을 물음표점(?.) 으로 바꾼 것이다.
* `엘비스 연산자`는 물음표 뒤에 콜론을 붙인 연산자이다. (?:) → `else-if` 구문임.
----
## 39. 널 아님 단언
* 어떤 참조가 null이 될 수 없다는 사실을 명시적으로 써놓은 것임.
* 느낌표 두개 (!!) 를 사용한다.
* 예시
```kotlin
fun main() {
    var x: String? = "abc"
    x!! eq "abc"
    x = null
    try {
        val s: String = x!!
    } catch (e: Throwable) {
        println(e)
    }
}
```
----
## 40. 확장 함수와 null이 될 수 있는 타입
* 코틀린 표준 라이브러리는 다음과 같이 String 의 확장 함수를 제공한다
  * isNullOrEmpty() : 수신 String이 null이거나 빈 문자열인지 검사한다.
  * isNullOrBlank() : isNullOrEmpty() + 온전한 공백 문자(space, tab, newline) 로만 구성되어 있는지 검사한다.
----
## 41. 제네릭스 소개
