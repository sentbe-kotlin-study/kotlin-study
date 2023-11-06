# 챕터1. 프로그래밍 베이직

### 01. Why kotlin?
- 객체지향과 함수형 프로그래밍을 모두 지원한다.
- 기존 자바 프레임워크를 완벽하게 지원한다.
- 오픈소스이며 주요 IDE와 빌드 시스템을 완전히 지원한다.
- 실용적이며 안전하고, 간결하며 상호운용성이 좋다. 
- NPE와 같은 흔히 발생하는 오류를 방지한다.

### 02. Hello, World
- <b>fun</b> : function의 짧은 버전이다. function은 코드의 집합이다.
- <b>main()</b> : 코틀린 프로그램의 엔트리 포인트. 'main'은 자동적으로 호출된다.
- println : 파라미터를 보여준 후에 커서를 다음 줄로 이동시킨다. (print는 같은 줄)
- 
```kotlin
fun main() {
    // body 
    println("Hello, world!")
}
```

### 03. var & val
- <b>var</b> : variable의 약어. 재할당이 가능. 
- <b>val</b> : value의 약어. 재할당 불가.

##### var
- 해당 변수에 저장되는 데이터 변겅 가능 

```kotlin
fun main() {
  var sum = 1
  sum = sum + 2
  sum += 3
  println(sum)
}
```

##### val
- immutable한 변수로, 가능한 var 대신에 val을 사용하자. 
- 
```kotlin
fun main() {
    val whole = 11
    // whole = 15 // Error   // [1]
}
```

### 04. Data Types
- type : 그 데이터를 어떤 식으로 사용할 것이라 의도했는지 정의. 
- type inference : 어떻게 우리가 그 데이터를 사용했는지에 따라 코틀린이 타입 추론을 한다.
```kotlin
val identifier: Type = initialization
```
다음과 같이 타입을 정의한 후 초기화 할 수 있다. 
```kotlin
val n: Int = 1
var p: Double = 1.2
```
##### type
1. Int: 정수
2. Double: 소수점
3. Boolean: true / false
4. String: 문자열 
5. Char: 유니코드 ('a'로 표현)
6. triple quotes string: 줄로 이어지는 문자열 ' """ """ '로 표현 

```kotlin
fun main() {
  val whole: Int = 11              // [1]
  val fractional: Double = 1.4     // [2]
  val trueOrFalse: Boolean = true  // [3]
  val words: String = "A value"    // [4]
  val character: Char = 'z'        // [5]
  val lines: String = """Triple quotes let
you have many lines
in your string"""                  // [6]
  println(whole)
  println(fractional)
  println(trueOrFalse)
  println(words)
  println(character)
  println(lines)
}
```

### 05.Functions
- 함수는 고윻한 이름을 갖고 있고, 다른 함수에서 해당 이름을 호출하여 실행될 수 있는 작은 프로그램.
- 코드를 재사용하는 가장 기본적인 방법

```kotlin
// 기본형태
fun functionName(p1: Type1, p2: Type2, ...): ReturnType {
    // lines of code
    return result
}
```
- p1, p2: 파라미터는 해당 함수에 전달하는 정보이다. 파라미터에는 식별자 이름: 해당 파라미터 유형으로 전달된다.
- return: 함수가 완료될 때 생성되는 결과. 의미있는 결과를 제공하지 않을 경우엔 생력하거나 <b>Unit</b> 사용.

함수는 또한 축약된 구문을 사용할 수 있다.
```kotlin
fun multiplyByThree(x: Int): Int = x * 3
fun multiplyByFour(x: Int) = x * 4 // 코틀린이 반환 타입 추론
```
- 단일 표현 식인 경우 중괄호 생략 가능

### 06. if Expressions
- if는 표현식을 테스트하여 true / false를 확인하고 결과에 따라 작업을 수행한다.
- 표현식을 미리 정의한 후 사용할 수도 있다.
```kotlin
// IfExpressions/If2.kt

fun main() {
  val x: Boolean = 1 >= 1
  if (x)
    println("It's true!")
}
/* Output:
It's true!
*/
```

### 07. String Templates
- String 내부에 값을 삽입할 수 있다. 식별자 이름 앞에 $를 붙이면 된다.
```kotlin
fun main() {
  val answer = 42
  println("Found $answer!")     // [1]
  println("printing a $1")      // [2]
}
```
- 프로그램 식별자가 아닌 문자열 앞에 $가 붙은 경우는 아무일도 일어나지 않는다.

### 08. Number Types
- 정수 타입 : Int, Long
- 부동소수점 수 타입 : Double
- 정수 상수는 기본적으로 Int이나 L을 붙이면 Long 타입이다. 상수에 소수점이 있으면 Double이다.
```kotlin
fun main() {
    val n = 1000
    val l = 1000L
    val d = 1000.0
    println("$n, $l, $d")
}
```

### 09. Boolean
- && (논리곱, and): 연산자 오른쪽과 왼쪽에 있는 Boolean 식이 모두 true 일 때만 true를 돌려준다.
- || (논리합, or): 연산자 오른쪽과 왼쪽에 있는 Booelan 식 중 하나라도 true면 true를 돌려준다.

```kotlin
fun isOpen1(hour: Int) {
  val open = 9
  val closed = 20
  println("Operating hours: $open - $closed")
  val status =
    if (hour >= open && hour < closed) // [1]
      true
    else
      false
  println("Open: $status")
}
```

다음과 같이 if 문을 더 간단한게 만들 수도 있다. 
하지만 이런 조건식은 헷갈리기 쉬우므로 항상 가독성을 유지하고 의도를 명시하자!

```kotlin
val status =
        if (hour >= open && hour < closed) // [1]
            true
        else
            false

val status = hour >= open && hout <= close
```
