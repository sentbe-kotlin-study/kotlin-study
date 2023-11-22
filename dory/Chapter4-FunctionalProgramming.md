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
