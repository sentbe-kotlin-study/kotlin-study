# 함수형 프로그래밍

### 44. 람다

람다는 부가적인 장식이 덜 들어간 함수. </br>
이름이 없고, 함수 생성에 필요한 최소한의 코드.

```kotlin
fun main() {
    val list = listOf(1, 2, 3, 4)
    val result = list.map({ n: Int -> "[$n]" }) // 얘가 람다
}
```

```kotlin
// 함수 파라미터가 람다 뿐이면 람다 주변의 괄호를 없앨 수 있다.
fun main() {
    val list = listOf('a', 'b', 'c')
    val result = list.map { "[${it.toUpperCase()}]" } eq
}

// 람다가 마지막 파라미터인 경우에는 람다를 인자 목록을 감싼 괄호 다음에 위치 시킬 수도 있다.
fun main() {
    val list = listOf(9, 11, 13, 32)
    list.joinToString(" ") { "[$it]" }
}

// 파라미터가 두개 인경우
fun main() {
    val list = listOf(9, 11, 13, 32)
    list.mapIndexed { index, element ->
        "[$index: $element]"
    }
}

// 파라미터가 없을수도 있다.
fun main() {
    run { -> trace("A Lambda")}
    run { trace("Without args") }
}
```
