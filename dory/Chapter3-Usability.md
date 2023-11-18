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

