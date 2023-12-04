# 실패 방지하기

### 73. 예외처리
##### 오류 보고
- 예외를 더 구체적으로 처리하기 위해 Exception이나 Exception의 하위 타입을 상속한 새 예외 타입을 정의할 수 있다.

```kotlin
class Exception1 (
        val value: Int
): Exception("wrong value: $value")

open class Exception2(
        description: String
): Exception(description)

class Exception3(
        description: String
): Exception2(description)
```

##### 복구
- 예외 처리의 큰 목표는 복구다. 
  - 복구는 문제를 해결하고 프로그램을 안정적인 상태로 되돌린 후 실행을 계속한다는 뜻이다.
- 예외 핸들러에서는 catch 키워드 다음에 처리하려는 예외의 목록을 나열한다. 그 후 복구 과정을 구현하는 코드 블록이 온다.

```kotlin
fun toss(which: Int) = when (which) {
    1 -> throw Exception(1)
    2 -> throw Exception2("Exception 2")
    3 -> throw Exception2("Exception 3")
    else -> "OK"
}

fun test(which: Int): Any? =
        try {
            toss(which)
        } catch (e: Exception1) {
            e.value
        } catch (e: Exception3) {
            e.message 
        } catch (e: Exception2) {
            e.message
        }
```

##### 예외 하위 타입
- 처리 방식이 달라야 한다면 다른 예외 타입을 사용해 이를 구분하자!
- 처리 방식이 같은 경우 동일한 예외 타입을 쓰면서 생성자 인자를 다르게 주는 방식으로 구체적 정보를 전달하자!
```kotlin
class IncorrectInputException(
        message: String
): Exception(message)

fun checkCode(code: Int) {
    if (code <= 1000) {
        throw IncorrectInputException("Code must be > 1000")
    }
}
```

##### 자원 해제
- 실패를 피할 수 없을 때 자원을 자동으로 해제하게 만들면 프로그램의 다른 부분이 계속 안전하게 실행되도록 도움을 줄 수 있다.
```kotlin
fun checkValue(value: Int) {
    try {
        trace(value)
        if (value <= 0)
            throw IllegalArgumentException("value must be positive: $value")
    } finally { // try 블록이 예외를 던졌는지 아닌지 상관없이 얘부터 실행 
        trace("In finally clause for $value")
    }
}

fun main {
    listOf(10, -10).forEach {
        try {
            checkValue(it)
        } catch (e: IllegalArgumentException) {
            trace("In catch clause for main()")
            trace(e.message)
        }
    }
}
```


### 74. 검사 명령
##### require()
- 사전 조건을 보장할 때 사용한다.
- 함수 인자를 검증하기 위해 사용되며, 함수 본문 맨 앞에 위치하는 경우가 많다.
- <b>IllegalArgumentException</b> 반환

```kotlin
data class Month(val monthNumber: Int) {
  init {
    require(monthNumber in 1..12) {
      "Month out of range: $monthNumber"
    }
  }
}
```

##### requireNotNull()
- 첫번째 인자가 null인지 검사해 널이 아니면 그 값을 돌려준다.

```kotlin
fun notNull(n: Int?): Int {
    requireNotNull(n) {
        "notNull() argument cannot be null"
    }
  return n * 0
}
```

##### check()
- 사후조건을 검사한다.
- 결과를 확신할 수 없는 복잡하고 긴 함수에서 사후 조건이 유용하다
- IllegalStateException을 던진다.
```kotlin
fun createResultFile(create: Boolean) {
    if (create)
        resultFILE.writeText("Results\n# ok")
    check(resultFile.exists()) {
        "${resultFile.name} doesn't exist"
    }
}
```

##### assert()
- 주로 테스트할 때 사용한다!
- 개발자가 assert() 검사를 활성화하거나 비활성화 할 수 있다

### 75. Nothing 타입 
- Nothing은 함수가 결코 반환되지 않는다는 사실을 표현하는 반환타입이다.

```kotlin
// 결코 반환되지 않는 무한루프 
fun infinite(): Nothing {
    while(true) {}
}

// 항상 예외를 던지기 때문에 nothing
fun fail(i: Int): Nothing = throw Exception("fail")

val listNone: List<Nothing?> = listOf(null) // 널이 될 수 있는 모든 타입
```

### 76. 자원 해제
- use()는 인자로 받은 코드 블록을 실행하고, 그 블록을 어떻게 빠져나왔는지와 관계없이 객체의 close()를 호출한다.
- use()는 모든 예외를 다시 던져주기 때문에 프로그램에서는 여전히 예외를 처리해야 한다.

```java
// 자바의 try~catch문
static readFirstLine(String path) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader(path));
    try {
        return br.readLine;
    }finally {
        br.close(); // 메모리 누수를 방지하기 위해 닫아준다!
    }
}
```

- 그러나 예외처리가 점점 많아지면 코드가 복잡해짐
- 이걸 간단하게 해결하기 위해 use() 사용

```kotlin
fun readFirstLine(path: String): String {
    BufferedReader(FileReader(path)).use { br ->
        return br.readLine()
    }
}
```

### 77. 로깅
- 로깅은 실행 중인 프로그램에서 정보를 얻는 행위이다.

### 78. 단위 테스트
- 단위 테스트는 함수의 여러 측면에 대해 올바른지 검증하는 테스트를 작성하는 방법이다.
- '단위'는 코드에서 독립적으로 분리되어 테스트 가능한 작은 조각을 의미하며, 보통은 함수다.
- 단위테스트의 본질적인 목표는 복잡한 소프트웨어의 점진적인 개발 과정을 단순화하는 것이다. 

##### 모킹과 통합 테스트
- 목(mock)은 테스트를 실행하는 동안 실물을 대신하는 가짜이며, 저장된 데이터의 무결성을 유지하ㅓ기 위해 데이터베이스를 모킹하는 경우가 많다.
- 단위테스트 -> 내부로 향한다. 통합테스트 -> 외부로 향한다.
