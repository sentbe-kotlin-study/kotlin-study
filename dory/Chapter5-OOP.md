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

### 56. 복잡한 생성자
- val이나 var은 파라미터 목록에 있는 파라미터에 붙이면, 그 파라미터를 프로퍼티로 만들어준다.
```kotlin
fun Alien(val name: String)

fun main() {
    val alien = Alien("Pencilvester")
    alien.name eq "Pencilvester"
}
```
- 생성 과정을 제어하고 싶으면 클래스 본문에 생성자 코드를 추가해라.
```kotlin
class Message(text: String) {
    private val content: String
    init { 
        counter += 10
        content = "[$counter] $text"
    }
    override fun toString() = content
}

fun main() {
    val m1 = Message("big")
    m1 eq "[10] big"
    val m2 = Message("hi")
    m2 eq "[20] hi"
}
```
