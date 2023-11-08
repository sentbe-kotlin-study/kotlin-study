# 챕터1. 프로그래밍 베이직

### 01. Why kotlin?
- 객체지향과 함수형 프로그래밍을 모두 지원한다.
- 기존 자바 프레임워크를 완벽하게 지원한다.
- 오픈소스이며 주요 IDE와 빌드 시스템을 완전히 지원한다.
- 실용적이며 안전하고, 간결하며 상호운용성이 좋다. 
- NPE와 같은 흔히 발생하는 오류를 방지한다.

### 02. Hello, World
> 용어 설명 
- <b>keyword</b>: 특별한 의미를 지니는 단어 
  - <b>fun</b>: function(함수)의 짧은 버전이다. function은 함수 이름을 사용해 실행될 수 있는 코드 모음.
  - <b>main()</b>: 코틀린 프로그램의 엔트리 포인트. 'main'은 자동적으로 호출된다.
  - println: 파라미터를 보여준 후에 커서를 다음 줄로 이동시킨다. (print는 같은 줄)

> 예시
```kotlin
fun main() {
    // body 
    println("Hello, world!")
}
```
*tip. 코틀린은 식 끝에 세미콜론을 붙이지 않아도 된다!*

### 03. var & val
> var 

- variable의 약어
- 변할 수 있는 수를 의미한다. 즉, 재할당이 가능하다. 
```kotlin
fun main() {
  // 여러 번의 재할당 가능
  var sum = 1
  sum = sum + 2
  sum += 3
  println(sum)
}
```

> val
- value의 약어.
- immutable한 변수로 재할당이 불가능하다.

```kotlin
fun main() {
    val whole = 11
    // whole = 15 // Error   
}
```
*tip. 일반적으로 val만 사용하면 프로그램을 확장하고 유지 보수하기가 더 쉬워진다. 그러나 문제를 해결하기에 너무 복잡해 지기도 한다. 그러나 var를 사용하지 않으면 프로그램이 더 안전해지고 신뢰성도 높아진다!*

### 04. Data Types
> 타입이란
- <b>type</b>
  - 해당 데이터를 어떻게 사용할 지를 코틀린에게 말해준다.
  - 타입은 어떤 식이 취할 수 있는 값의 집합을 제공한다.
- <b>type inference</b>
  - 코틀린은 각 값을 어떻게 쓰는지 살펴보고 각 변수의 타입을 알아내서 우리에게 알려준다.
  
```kotlin
val identifier: Type = initialization
val n: Int = 1
var p: Double = 1.2
```
> 기본 타입 
1. Int: 정수
2. Double: 소수가 있는 수 
3. Boolean: true / false
4. String: 문자열 
5. Char: 유니코드 ('a'로 표현, 한 문자)
6. triple quotes string: 여러 줄로 이어지는 문자열 ' """ """ '로 표현 

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
> 함수란
- 함수는 고유한 이름을 갖고 있고, 다른 함수에서 해당 이름을 호출하여 실행될 수 있는 작은 프로그램.
- 코드를 재사용하는 가장 기본적인 방법.

> 함수의 구성
- p1, p2: 파라미터는 해당 함수에 전달하는 정보이다. 파라미터에는 '식별자 이름: 해당 파라미터 유형'으로 전달된다.
- return: 함수가 완료될 때 생성되는 결과. 의미있는 결과를 제공하지 않을 경우엔 생력하거나 <b>Unit</b> 사용.
- 함수 시그니처: 이름, 파라미터, 반환 타입을 합쳐서 함수 시그니처라고 부른다. 함수 시그니처를 가지고 컴파일러가 어떤 함수가 호출될 것인지 인식한다.
```kotlin
// 기본형태
fun functionName(p1: Type1, p2: Type2, ...): ReturnType {
    // lines of code
    return result
}
```
> 본문의 축약

함수 본문이 하나의 식으로만 이루어진 경우, 등호 뒤에 식을 넣어 함수를 짧게 작성할 수 있다. 
```kotlin
fun multiplyByThree(x: Int): Int {
    return x * 3
}

fun multiplyByThree(x: Int): Int = x * 3
```

### 06. if Expressions
- if는 표현식을 테스트하여 true / false를 확인하고 결과에 따라 작업을 수행한다.
- if 뒤에 있는 괄호 안의 식은 반드시 true / false로 평가되어야 한다.
- 표현식을 미리 정의한 후 사용할 수도 있다.

```kotlin
fun main() {
  val x: Boolean = 1 >= 1 //식을 미리 정의
  if (x)
    println("It's true!")
}
```

- 다음과 같이 return의 생략도 가능하다.
```kotlin
fun oneOrTheOther(exp: Boolean): String =
  if (x)
      "True!"
  else 
      "False"
```
### 07. String Templates
- String 내부에 값을 삽입할 수 있다. 식별자 이름 앞에 $를 붙이면 된다.
- 프로그램 식별자가 아닌 문자열 앞에 $가 붙은 경우는 아무일도 일어나지 않는다.
```kotlin
fun main() {
  val answer = 42
  println("Found $answer!")     // [1]
  println("printing a $1")      // [2]
}
```
- ${}의 중괄호 안에 식을 넣으면 그 식을 평가한 후 그 식의 결과 값을 넣는다.
```kotlin
fun main() {
    val condition = true
  println(
    "${if (condition) 'a' else 'b'}"
  ) // a 출력
} 
```

### 08. Number Types
> 기본 수 타입

- 정수 타입 : Int, Long
- 부동소수점 수 타입 : Double
- 정수 상수는 기본적으로 Int이나 L을 붙이면 Long 타입이다. 상수에 소수점이 있으면 Double이다.
```kotlin
fun main() {
    val n = 1_000_000 // int
    val l = 1000L // Long
    val d = 1000.0 // Double 
    println("$n, $l, $d")
}
```
> 고려할 점

- 정수 나눗셈에서 나머지를 처리하는 방법은 '버림'이다. -> Double 사용
- 값의 범위를 초과할 경우 '넘침'이 발생한다. 
  - 넘침: 음수이면서 예상보다 보다 훨씬 작은 값 == 틀린 결과 
  
*tip. 가독성을 위해 코틀린은 숫자 사이에 밑줄(_)이 허용된다.*

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

다음과 같이 if 문을 더 간단하게 만들 수도 있다. 
하지만 이런 조건식은 헷갈리기 쉬우므로 항상 가독성을 유지하고 의도를 명시하자!

```kotlin
val status =
        if (hour >= open && hour < closed) // [1]
            true
        else
            false

val status = hour >= open && hout <= close
```
### 11. while
- while문은 주어진 Boolean식이 true인 동안 블록을 반복 수행한다.
```kotlin
fun main() {
    var i = 0
    while (condition(i)) {
        print(".")
        i += 10
    }
}
```
- do-while: boolean 식이 false를 돌려줘도 본문이 최소 한번은 실행된다.
- while: 처음에 조건문이 false면 본문이 결코 실행되지 않는다.
```kotlin
fun main() {
    var a: Int = 1
    while (a <= 10) {
        print("${a++}")    //output : 1, 2, 3, 4, 5 ... 10
    }
    
    do {
        print("${a--}")    //output : 11, 10, 9, 8, 7 ... 1
    } while(a > 0)
}
```

### 12. 루프와 범위

- for: 주어진 순열에 속한 각 값에 대해 코드 블록을 실행한다.
  - 범위: 양 끝을 표현하는 두 정수를 사용해 구간을 정의한 것이다
    - .. : 양 끝 값을 포함
    - until: 끝 값 제외 
```kotlin
fun main() {
  for (i in 1..3) { // = 1 until 4 
    println("Hey $i")
  }
}
```
- downTo: 감소하는 범위를 만든다.
- step: 간격을 변경한다.
```kotlin
fun main() {
  for (i in 10 downTo 1 step 3) {
    print("$i")
  }
}
```

