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