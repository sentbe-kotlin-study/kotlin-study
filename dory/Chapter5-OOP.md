# OOP

### 55. 인터페이스
- 클래스가 무엇을 하는지 기술한다. (= 형태를 제시한다.)

```kotlin
interface Computer {
    fun prompt(): String
    fun calculateAnswer(): Int
}

class Desktop : Computer {
    override fun prompt(): String {TODO("Not yet implemented") }
    override fun calculateAnswer(): Int {TODO("Not yet implemented") }
}
```
- Computer는 prompt와 calculateAnswer()를 선언하지만 아무 구현도 제공하지 않는다.
- 이 인터페이스를 구현하는 클래스는 인터페이스가 선언한 모든 멤버를 구현하는 본문을 제공해야 한다.

##### SAM 변환
- 단일 추상 메서드 (Single Abstract Method) 인터페이스는 자바 개념으로, 자바에서는 멤버 함수를 '메서드'라고 부른다.
- SAM 인터페이스 구현 방식
  - 클래스를 통해 구현
  - 람다를 넘기는 방식으로 구현 -> SAM 변환이라고 한다

```kotlin
fun interface ZeroArg {
    fun f(): Int
}

// 클래스 통해 구현
class VerboseZero : ZeroArg {
    override fun f() = 11
}
val verboseZero = VerboseZero()

// 람다 구현
val samZero = ZeroArg { 11 }
```

- 람다를 SAM 인터페이스가 필요한 곳에 넘길 수도 있다.

```kotlin
fun interface Action {
    fun act()
}

fun delayAction(action: Action) {
    action.act()
}
```