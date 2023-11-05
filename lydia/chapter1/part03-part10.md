#### 챕터1. 프로그래밍 베이직

## 03. Hello, World!
```kotlin
fun main() { // kotlin 의 fun 키워드: function
    println("Hello, world!")
}
```


***


## 04. var와 val
- var: 변할 수 있는 수(variable)의 약어. 내용을 재대입 할 수 있다.
    - 가변 `mutable` 변수
- val: 값(value)의 약어. 한 번만 초기화할 수 있다.
    - 불변 `immutable` 변수
```kotlin
fun main() {
    val a = 1 // 불변변수의 초기화
    a = 15 // Error - val cannot be reassigned
    
    var b = 2
    b = 25 // 가변변수이므로 성공
}
```


***


## 05. 데이터 타입
- 대상 데이터를 어떤 식으로 사용할지 데이터의 타입을 정의한다.
    - 콜론(`:`)을 이용하여 데이터 타입을 지정할 수 있다.
    - 변수의 데이터 타입을 지정하지 않아도 `타입추론`을 통해 컴파일 되고 실행된다.
- 여러 줄에 걸친 문자열을 만들거나 특수 문자가 들어간 문자열을 만들어야 하는 경우
    - `삼중 큰 따옴표`를 사용한다. (raw String 이라고도 칭함)
> 데이터 초기화 방식 예시
```kotlin
fun main() {
    var b:Int = 2
    var c:String = "Hello, World!"
    var lines: String = """
        삼중 따옴표는
        내부에 있는 들여쓰기나 줄바꿈등의
        모든 문자를 포함합니다.
    """
}
```
> 타입추론 예시
```kotlin
fun main() {
    val a = 1  // a 는 Int 데이터 타입
    val rank:Int = 1
    val fee:Double = 30.5
    val result = rank + fee // 결과값 result 는 Double 데이터 타입
    println(result) // 31.5
}
```


***


## 06. 함수
> 기본적인 함수 정의 예시
```kotlin
fun 함수이름(p1: 타입1, p2: 타입2, ...): 반환타입 {
    // code
    return 결과
}
```

- Unit: **의미있는 결과를 제공하지 않는다**는 것을 명시적으로 선언하기 위한 함수의 반환 타입
    - Java의 void와 비슷한 개념이나, 상이한 점이 존재한다. 추후 제네릭을 공부하면 차이점을 알 수 있다.
- Noting: **의미 있는 데이터가 없다**는 것을 명시적으로 선언하기 위한 함수의 반환 타입
> 함수의 Unit 반환 타입 사용 예시
```kotlin
fun printFun(arg: Unit): Unit {
    println("데이터를 반환하지 않습니다.")
}
```
> 함수의 Noting 반환 타입 사용 예시
```kotlin
fun checkFun(arg: Nothing): Nothing {
    throw Exception()
}
```


***


## 07. if 식
 
```kotlin
fun main() {
  val n: Int = -11
  if (n > 0)
      println("It's positive")
  else
      println("It's negative or zero")
}
```
```kotlin
fun main() {
  val y: Boolean = false
  if(!y)
      println("!y is true")
}
```
```kotlin
fun main() {
  val num = 10
  val result = if (num > 100) 100 else 0
  println(result) // 0
}
```
```kotlin
fun trueOrFalse(exp: Boolean): String {
  if(exp)
      return "It's true!"
  return "It's false"
}
fun main() {
    val b = 1
    println(trueOrFalse(b < 3)) // It's true!
    println(trueOrFalse(b >= 3)) // It's false!
}
```
```kotlin
fun trueOrFalse(exp: Boolean): String = {
  if (exp)
      "It's true!" // 'return' 을 쓰지 않아도 된다
  "It's false"
}
fun main() {
    val b = 1
    println(trueOrFalse(b < 3)) // It's true!
    println(trueOrFalse(b >= 3)) // It's false!
}
```


***


## 08. 문자열 템플릿
- 식별자 앞에 `$`를 붙이면, 문자열 템플릿이 그 식별자의 내용을 String에 넣어준다.
```kotlin
fun main() {
    val a = "hello!\n"
    val b = 100
    val c = 3.14
    println("a: $a" + "b: $b," + "c: $c")
    println("a: $a" + "b: $b, " + "c: $c")
    // a: hello!
    // b: 100, c: 3.14
}

```
- `$` 뒤에 중괄호를 붙이고 (`${}`) 그 안에 식을 넣으면 그 식을 평가한다.
```kotlin
fun main() {
    val condition = false
    println("${if (condition) 'a' else 'b'}") // b

    val a = 11
    println("$a + 4 = ${a + 4}") // 11 + 4 = 15
}
```

- String 안에 큰따옴표 등의 특수 문자를 넣어야 하는 경우에는 `역슬래시(\)`를 사용해 이스케이프(escape)하거나 큰따옴표를 연달아 세 개 쓰는 String 리터럴을 사용해야 한다.
  - 큰따옴표 세 개를 쓸 때도 큰따옴표 하나만 쓰는 문자열과 마찬가지로 `$` 식별자나 `${식}`을 사용해 식의 값을 삽입할 수 있다.
```kotlin
fun main() {
    val a = "hello"
    println("a = \"$a\".") // "hello".
    println("""a = $a.""") // hello.
}
```


***


## 09. 수 타입


***

