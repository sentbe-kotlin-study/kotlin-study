# 챕터2. 객체 소개

### 16. 객체는 모든 곳에 존재한다.

> 용어

- 클래스: 새로운 데이터 타입의 기초가 될 프로퍼티와 함수 정의
- 멤버: 클래스에 속한 프로퍼티나 함수
- 멤버 함수: 특정 클래스에 속한 객체가 있어야만 사용할 수 있는 함수
- 객체 생성: 클래스에 해당하는 val이나 var 값을 만드는 과정

### 17. 클래스 만들기

> 문제를 해결할 때 필요한 개념을 표현하는 객체를 생각하는 것부터 시작해라

##### 클래스

- 코틀린에서는 class 라는 키워드를 사용해 새로운 유형의 객체를 만들어낸다.
- 클래스를 정의할 때는 class 키워드 다음에 클래스 이름을 넣는다.

```kotlin
class Giraff // 클래스 정의

fun main() {
    // 객체 생성
    // 객체는 각각 고유한 정체성을 가진다.
    val g1 = Giraff()
    val g2 = Giraff()
}
```

##### 데이터 클래스

- 생성과 동시에 클래스 내의 프로퍼티를 기준으로 생성자가 만들어진다.
- toString(), copy(), hashCode(), equals()를 자동으로 구현해준다.
- 상속이 불가능하다.

##### 함수

- 코틀린에서는 메서드가 아닌 함수라는 표현을 사용한다.
  - 멤버 함수: 클래스에 속한 함수
  - 최상위 함수: 클래스에 속하지 않는 함수

```kotlin
class Dog {
    // 멤버 함수
    fun bark() = "yip"
}
```

- 멤버함 수를 부를 때는 객체 이름 다음에 .과 함수이름, 파라미터 목록을 나열한다.

```kotlin
fun main() {
    val dog = Dog()
    println(dog.bark())
}
```

### 18. 프로퍼티

> 프로퍼티는 클래스에 속한 val이나 var이다

- 프로퍼티를 정의함으로써 클래스 안에서 상태를 유지한다. -> 클래스를 작성하는 주된 이유

```kotlin
class Cup {
    var percentFull = 0
}

fun main() {
    val c1 = Cup()
    c1.percentFull = 50
    val c2 = Cup()
    c2.percentFull = 100
}
```

- 최상위 프로퍼티도 정의할 수 있다.
- var인 최상위 프로퍼티를 선언하는 것은 안티패턴이다. 프로그램이 복잡해질수록 공유된 가변 상태에 대해 제대로 추론하기가 어려워진다. -> 가변상태를 클래스 안에 가두는 것이 가장 좋다.

```kotlin
val counter = 0

fun inc() {
    conuter++
}
```

### 19. 생성자

- 생성자: 새 객체를 초기화한다.
- 파라미터: 정보를 전달할 땐 파라미터를 이용한다.
  - 파라미터에 var, val을 붙이면 본문 밖에서도 접근이 가능하다.

```kotlin
// name에 접근 불가
class Alien(name: String) {
    val greeting = "Poor $name!"
}

// name에 접근 가능
class MutableNameAlien(var name: String)
```

### 20. 가시성 제한하기

> 리팩터링을 하는 주된 이유: 유지보수하기 쉽게 만들자!

코드 변경 개선 욕구 -> 하지만 안정적이어야 함. -> 변화해야 하는 요소와 유지되어야 하는 요소를 분리!
가시성을 제어하기 위해 <b>접근 변경자</b>를 제공한다. 이런 접근 변경자를 사용해 어떤 부분에 접근할 수 있고 없는지를 결정한다.

##### private

- private 키워드는 같은 클래스에 속한 멤버 외에는 아무도 이 멤버에 접근할 수 없다는 뜻이다.
- 어떤 클래스에서 도우미 함수로 쓰이는 멤버 함수를 private로 선언하면 클래스 외부에서 이 함수를 실수로 쓰는 경우를 방지할 수 있다.

```kotlin
class Cookie(
        private val isReady: Boolean
) {
    private fun crumble() =
            println("crumble")

  public fun bite() =
          println("bite")

  fun eat() {
      isReady = true
    crumble()
    bite()
  }

  fun main() {
      val x = Cookie(false)
    x.bite()
    // private 멤버에는 접근할 수 없다.
    x.eat()
  }
}
```

### 21. 패키지

> 패키지는 연관 있는 코드를 모아둔 것이다.

- import 키워드를 써서 다른 파일에 정의된 코드를 재사용할 수 있다.

```kotlin
import packagename.ClassName ,,,
```

- as 키워드를 이용하면 임포트하면서 이름 변경이 가능하다

```kotlin
import kotlin.math.PI as circleRatio

fun main() {
    println(circleRatio)
}
```

- 코드를 재사용하려면 package 키워드를 사용해 패키지를 만들어라

```kotlin
package pythagorean

// other file
import pythagoran.fun ,,,
```

### 22. 테스트

> 테스트는 소프트웨어 개발 과정에 포함되어 있어야 가장 효과적이다!

##### 테스트 주도 개발

코드를 작성하기 전에 테스트를 먼저 작성해 실패시킨 후, 나중에 테스트를 통과하도록 코드를 작성한다.
이런 기법을 테스트 주도 개발(TDD)라고 한다. TDD는 자신이 생각하는 대상을 정말로 테스트하고 있는지 확실히 확인할 수 있는 방법이다.

### 23. 예외

- 예외가 던져지면 실행 경로가 중단되고, 예외 객체는 현재 문맥을 벗어난다.
- 예외를 잡아내지 않으면 프로그램이 중단되면서 예외에 대한 상세정보가 들어있는 스택트레이스가 출력된다.

```kotlin
fun erroneousCode() {
    val i = "1$".toInt() // 예외 발생 코드
}

fun main() {
    erroneousCode()
}
```

### 24. 리스트

> list: 다른 객체를 담는 객체

컨테이너는 컬렉션 이라고도 한다.

```kotlin
val ints = listof(1,2,3) // 리스트 생성
for (i in ints) {
    // iteration
}
ints[4] // 인덱싱
```

_tip. 리스트의 마지막 원소보다 더 큰 인덱스를 넣으면 ArrayIndexOutOfBoundException을 던진다._

##### 가변 List

가변 list는 필요하다고 명시적으로 표시해야 한다.

- listof(): 읽기전용 리스트
- mutableListOf(): 변경가능한 리스트

```kotlin
val list = mutableListOf<Int>()
```

##### += 의 비밀

- list가 var일 경우 가변리스트처럼 보인다.

```kotlin
fun main() {
    var list = listOf('X') // 불변 리스트
    list += 'Y' // 가변리스트처럼 보임
    list eq "[X, Y]"
}
```
