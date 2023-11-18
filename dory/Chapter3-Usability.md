# 사용성 (Usability)

### 30. 확장 함수
- 기존 클래스에 멤버 함수를 추가하는 것과 같은 효과를 낸다.
- 확장할 대상 타입은 수신 객체 타입이라고 한다. 

```kotlin
fun 수신타입.확장함수() {} // 선언방법 

// 스트링 클래스에 singleQuote 함수를 추가하는 것처럼 보인다.
fun String.singleQuote() = "'$this'"
```

- 자신이 만든 클래스에도 추가 가능하다.

```kotlin
class Book(val title: String)

fun Book.categorize(category: String) =
        """title: "$title", category: $category"""
```

### 31. 이름 붙은 인자와 디폴트 인자
- 이름 붙은 인자를 사용하면 코드 가독성이 좋아진다.
- 이름을 붙이면 순서 상관없다.
- 이름 붙은 인자와 일반 인자를 섞어서 사용할 수 있지만, 순서를 변경하면 나머지에도 이름 붙은 인자를 사용해야 한다. 

```kotlin
fun color(red: Int, green: Int, ..)

color(
        red = 76,
        green = 89 ,... 
)
```

- 이름 붙은 인자는 디폴트 인자와 결합하면 더 유용하다.

```kotlin
fun color (
        red: Int = 0,
        green: Int = 0, 
        ... 
)
```

- joinToString()은 디폴트 인자를 사용하는 표준 라이브러리 함수다.
- 이터레이션이 가능한 객체의 내용을 String으로 합쳐준다. 

```kotlin
fun main() {
    val list = listOf(1,2,3)
    list.toString() eq "[1,2,3]"
    list.joinToString() eq "1,2,3"
}
```

### 32. 오버로딩
- 파라미터 목록이 여러 다른 함수에 같은 이름을 사용하는게 오버로딩이다.

```kotlin
class Overloading {
    fun f() = 0
    fun f(n: Int) = n+2
}
```
##### 주의점
- 멤버와 시그니처가 중복되는 확장 함수를 호출할 수 없다.
- 오버로딩과 디폴트 인자를 함께 사용하는 경우 함수 시그니처와 함수 호출이 '가장 가깝게' 일치되는 함수를 호출한다.


### 33. when 식
_tip. 두세 가지 이상의 선택지가 있는 경우 when 식을 사용하면 좋다!_

```kotlin
fun ordinal(i: Int): String =
        when(i) {
            1 -> "erste"
            3 -> "dritte"
            else -> numbers.getValue(i) + "te"
        }
```

- else를 없애면 컴파일 오류가 발생한다. 
- when 식을 문처럼 사용하는 경우에만 else를 생략할 수 있다.

```kotlin
when (input) {
    "up", "u" -> coordinates.y-- // 2개 표현 가능
    "right", "r" -> {
        // 여러개 일 경우 중괄호
    }
    "right", "r" -> {} // 아무것도 안할 경우 빈 중괄호
    "exit" -> return // 해당 프로세스 종료
}
```

### 34. 이넘
> 이넘은 이름을 모아둔 것이다

- 이넘은 인스턴스 개수가 미리 정해져 있고 클래스 본문안에 이 모든 인스턴스가 나열되어 있는 특별한 종류의 클래스다.

```kotlin
enum class Level {
    Overflow, High, Medium, Low, Empty
}
```

- 멤버함수나 멤버 프로퍼티의 정의도 가능하다. 

```kotlin
enum class Direction(val notation: String) {
    North("N"), South("S");
    val oppsite: Direction
        get() = when(this) {
            North -> South
        }
}

fun main() {
    North.opposite eq South
}
```

### 35. 데이터 클래스
> 데이터 저장만 담당하는 클래스
- 모든 생성자 파라미터를 var이나 val로 선언해야 한다.
- data class에서는 equals(), copy()가 자동으로 생성된다.

```kotlin
data class Simple(
        val arg1: String
)
```

### 36. 구조 분해 선언
- 하나 이상의 아이템을 반환하고 싶으면 ? -> Pair 사용
```kotlin
fun compute(input: Int): Pair<Int, String> = 
        if (input > 5) Pair(input * 2, "High")
```
- 페어의 값을 얻고 싶으면, first/second도 있지만 구조 분해 선언이 가능하다
```kotlin
val (a,b,c) = 여러_값이_들어있는_값

val (value, description) = compute(7)
```
- 데이터 클래스를 사용하는 방법도 있다. 
```kotlin
data class Computation(
        val data: Int,
        val info: String
)
fun compute(input: Int): Computation =
        if (input > 5) Computation(input * 2, "High")

```
- for 루프를 사용할 수도 있다
```kotlin
// mapOf(1 to "one", ...)
for ((key, value) in map) { }
// listOf(Pair(1, "one"), ...)
for ((i, s) in listOfPairs) {}
// listOf('a', 'b', 'c')
for ((index, value) in list.withIndex()) {} // python의 enumerate
```

### 37. 널이 될 수 있는 타입
- ㅋ코틀린에서 모든 타입은 기본적으로 널이 될 수 없는 타입이다.
- 하지만 '?'를 붙여서 null을 허용할 수도 있다.
- String과 String?은 다른 타입이다. 

```kotlin
val s1 = "abc"
val s3: String? = null 
```

- null이 될 수 있는 타입의 멤버를 참조하는 경우, if문 검증이 필수다.
```kotlin
val s1: String? = "abc"
s1.length eq 3 // compile x

// 다음과 같이 if문 추가 필요 
if (s!=null)
    s1.length eq 3
```

### 38. 안전한 호출과 엘비스 연산자 
- 안전한 호출은 일반 호출에 사용하는 점을 물음표와 점으로 바꾼 것이다. 
- 안전한 호출을 사용하면 널이 될 수 있는 타입의 멤버에 접근하면서 아무 예외도 발생하지 않게 해준다. 
```kotlin
// 1
if (s!=null)
    s1.length else null
//2
s1?.length 
```
- 엘비스 연산자 : ?: 의 왼쪽 식의 값이 null이 아니면 왼쪽 식의 값이 결과값, null이면 오른쪽 식이 결과값
```kotlin
val s1: String? = "abc"
(s1 ?: "---") eq abc // s1이 null이 아니면 s1, null이면 "---"
```

- 보통 안전한 호출 다음에 엘비스 연산자를 사용한다.
```kotlin
val length = s?.length ?: 0
```

### 39. 널 아님 단언
- null이 될 수 없다고 주장하기 위해 느낌표 두개 (!!) 쓰는 것 == 널 아님 단언
- x!! == x가 null일 수도 있단느 사실을 무시하라 -> x가 null이면 오류 
```kotlin
// 보통 역참조와 함께 쓰임
fun main() {
    val s: String? = "abc"
    s!!.length eq 3
}
```
_tip. 널 아님 단언을 사용하지 않고 안전한 호출이나 명시적인 null검사를 활용하자._

### 40. 확장 함수와 널이 될 수 있는 타입
- 코틀린이 제공하는 String의 확장함수 
  - isNullOrEmpty()
  - isNullOrBlank()

```kotlin
val s1: String? = null
s1.isNullOrEmpty()
```

### 41. 제네릭스 소개
- 제네릭스: 파라미터화한 타입 (여러 타입에 대해 작동할 수 있는 컴포넌트)
```kotlin
// 제네릭 타입 정의 
class GenericHolder<T>(
        private val value: T
)
```
- 유니버셜 타입: 모든 타입의 부모 타입 (Any)
```kotlin
// 유니버셜 타입 정의 
class AnyHolder(
        private val value: Any
)
```
- 간단한 경우에는 Any가 작동하나 구체적인 타입이 필요해지면 (Dog의 bark() 호출) 제대로 작동하지 않는다.
- 여기서 제네릭스를 사용하면 Dog 추론 가능!
```kotlin
fun <T> identity(arg: T): T = arg
```

### 42. 확장 프로퍼티
- 확장 함수처럼 확장 프로퍼티를 정의할 수도 있다.
```kotlin
// 커스텀 게터가 필요하다.
val String.indices: IntRange
  get() = 0 until length

fun main() {
    "abc".indices eq 0..2
}
```

_tip. 기능이 단순하고 가독성을 향상시키는 경우에만 프로퍼티를 권장한다._

- 제네릭 확장 프로퍼티를 정의할 수도 있다.
```kotlin
val <T> List<T>.firstOrNull: T?
  get() = if (isEmpty()) null else this[0]
```

##### * (star projection)
- *는 어떤 타입이 들어올지 미리 알 수 없어도 그 타입을 안전하게 사용하고 싶을 때 사용한다. -> 타입은 중요하지 않고 읽기만 하는 경우 등에 사용한다. 
- 언제든지 모든 타입을 받을  수 있는 Any와 다르게 한번 구체적인 타입이 정해지고 나면 해당 타입만 받을 수 있다.
- Any: 모든 타입 가능, *: 그저 타입이 정해지지 않았을 뿐이다.

```kotlin
fun printValues(values: Array<*>) {
    for (value in values) {
        println(value)
    }
    values[0] = values[1] // ERROR (T는 가능)
}
```

### 43. break와 continue
- break나 continue를 사용하는 대신 이터레이션 조건을 명시적으로 작성해라.
