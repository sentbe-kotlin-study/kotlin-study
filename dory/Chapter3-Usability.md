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

