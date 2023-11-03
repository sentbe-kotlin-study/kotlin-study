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

### 06. if Expressions

### 07. String Templates

### 08. 