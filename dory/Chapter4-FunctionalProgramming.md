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

### 45. 람다의 중요성

람다를 사용하면 ... 반복을 피하면서 간결하고 명확한 코드의 작성이 가능하다! </br>
람다는 개발자가 직접 처리해야 할 이터레이션을 처리해준다. </br>
이는 함수형 프로그래밍이 제공하는 특징이기도 하다. ex. map(), filter() </br>

```kotlin
fun main() {
    val list = listOf(1, 2, 3, 4)
    val isEven = { e: Int -> e % 2 == 0}
    list.filter(isEven) eq listOf(2,4)
    list.any(isEven) eq true
}
```

람다는 자신의 영역 밖에 있는 요소를 참조할 수 있는 능력이 있다! </br>

```kotlin
fun main() {
    val list = listOf(1, 5, 7, 10)
    var sum = 0
    val divider = 5
    list.filter { it % divider == 0}.forEach{ sum += it } // 외부변수 sum을 참조하고 변경한다.
}
```

### 46. 컬렉션에 대한 연산

> 함수형 프로그래밍은 객체 컬렉션에 대한 연산을 한꺼번에 수행할 수 있는 능력이 중요하다.

람다를 이용한 리스트 초기화 방법들

```kotlin
val list1 = List(10) { it }
val list2 = List(10) { 0 }
val list3 = List(10) { 'a' + it }
val list4 = List(10) { list3[it % 3] }
```

```kotlin
val mutableList1 = MutableList(5, {10 * (it + 1)})
val mutableList2 = MutableList(5) {10 * (it + 1)}
// 결과는 동일하게 [10, 20, 30, 40, 50]
```

##### 람다의 함수들

- filter(): 주어진 술어와 일치하는 모든 원소가 들어 있는 새 리스트 생성
- any(): 원소 중 어느 하나에 대해 술어가 true -> true
- all(): 모든 원소가 술어와 일치
- none(): 술어와 일치하는 원소가 하나도 없는지
- find(): 술어와 이치하는 첫번째 원소
- lastOrNull(): 술어와 일치하는 마지막 원소
- count(): 술어와 일치하는 원소의 개수
- partition(): 술어를 만족하는 원소들과 만족하지 않는 원소들 -> Pair

### 47. 멤버 참조

함수 프로퍼티 생성자에 대해 만들 수 있는 멤버참조

```kotlin
data class Message(
    val sender: String,
    val text: String,
    val isRead: Boolean
)

val unread = messages.filterNot(Message::isRead) // 멤버 참조
```

##### 함수 참조

- 람다를 별도의 함수로 추출 -> 함수에 대한 참조를 넘김

```kotlin
fun Message.isImportant(): Boolean =
    text.contains("Salary increase") ||
        attachments.any {
            it.type == "image"
        }

fun main() {
    messages.any(Message::isImportant)
}
```

##### 생성자 참조

- 클래스 이름을 사용해 생성자에 대한 참조를 만든다.

```kotlin
data class Student(
    val id: Int,
    val name: String
)
// index를 id, 이름을 name
names.mapInddexed(::Student)
```

##### 확장함수 참조

참조 앞에 확장 대상 타입 붙이기

```kotlin
fun Int.times47() = times(47)

fun main() {
    goInt(12, Int::times47)
}
```

### 48. 고차 함수

> 함수를 다른 함수의 인자로 넘길 수 있거나 함수가 반환 값으로 함수를 돌려줄 수 있으면 -> 고차함수

```kotlin
val isPlus: (Int) -> Boolean = { it > 0 }
```

### 49. 리스트 조작하기

##### 묶기

- zip()은 두 list의 원소를 하나씩 짝짓는 방식으로 묶는다.
- 두 시퀀스 중 어느 한쪽이 끝나면 묶기 연산도 끝난다.

```kotlin
[(a, 0), (b, 1), ...]
```

- zipWithNext()는 인접한 다음 원소를 묶는다.

##### 평평하게 하기

- flatten(): list 안 list 를 flat
- flatMap(): map + flatten


### 50. 맵 만들기
맵을 사용하면 키를 사용해 값에 빠르게 접근 가능
- groupBy(): 원소를 분류하는 기준을 키로 삼아 map을 만든다
- associateWith(): 리스트 원소를 키로 하는 맵

```kotlin
fun main() {
    val map: Map<Int, List<Person>> = people().groupBy(Person::age)
    map[15] eq listOf(Person("Arth", 15))
    
    val map: Map<Person, String> = people().associateWith { it.name }
    map eq mapOf(
            Person("Alice", 21) to "Alice"
    )
}
```

### 51. 시퀀스
- 즉시계산: 모든 원소에 대해 바로 계산이 이루어짐
  - ex. 필터링 -> 맵 -> any
- 지연계산: 결과가 필요할 때만 계산을 수행한다.
  - ex. 원소와 연관된 값만 계산

List를 시퀀스로 변경하면 지연 계산이 활성화 됨 !!  -> 더 적은 연산

```kotlin
list.asSequence()
```

- generateSequence(): 자연수로 이루어진 무한 시퀀스를 만든다
```kotlin
fun main() {
    val naturalNumbers = generateSequence(1) { it+1} // 1부터 하나씩 증가시켜라
    naturalNumbers.take(3).toList() // 3개까지 리스트로 출력 (1, 2, 3)
}
```

### 52. 지역 함수
- 지역 함수: 다른 함수 안에 정의된 이름 붙은 함수
```kotlin
fun main() {
    val logMsg = StringBuilder()
    fun log(message: String) = logMsg.appendLine(message) // main 함수 안에 내포
}
```

- 익명함수: 이름이 없는 함수
```kotlin
fun main() {
    sessions.any (
            fun(session: Session): Boolean { // 익명 함수
                if (session.title.contains("Kotlin") && session.speaker in favoriteSpeakers) {
                    return true
                }
              return false
            })
}
```

##### 레이블
람다를 둘러싼 함수가 아니라 람다에서만 반환해야 한다면 레이블이 붙은 return을 사용해라!

```kotlin
fun main() {
    // case 1
    val list = listOf(1,2,3,4,5)
    val value = 3
    val result = ""
    list.forEach {
        result += "$it"
        if (it == value) {
            result eq "123"
            return // main 함수를 끝낸다!!
        }
    }
  
    // case2
    val list = listOf(1,2,3,4,5)
    val value = 3
    val result = ""
    list.forEach {
      result += "$it"
      if (it == value) {
        result eq "123"
        return@forEach // forEach까지만 반환
      }
    }
}
```

- 지역함수 조작하기
```kotlin
// 익명함수 
fun first(): (Int) -> Int {
  val func = fun(i: Int) = i + 1
  func(1) eq 2
  return func
}

// 람다
fun second(): (String) -> String {
  val func2 = { s: String → “$s!” }
  func2("abc") eq "abc!"
  return func
}

// 지역함수 
fun third(): () -> String {
  fun greet() = “Hi!”
  return ::greet
}

fun main() {
  val funRef1: (Int) -> Int = first()
  val funRef2: (String) -> String = second()
  val funRef3: () -> String = third()
  
  funRef1(42) eq 43
  funRef2("xyz") eq "xyz!"
  funRef3() eq "Hi!"
}
```


