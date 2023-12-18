# 7. Power Tool

### 79. 확장 람다

- 확장 람다는 확장 함수와 비슷하다. 차이가 있다면 함수가 아니라 람다라는 점이다.

```kotlin
class A {
    fun af() = 1
}

class B {
    fun bf() = 2
}

fun f1(lambda: (A, B) -> Int) =
    lambda(A(), B())

fun f2(lambda: A.(B) -> Int) =
    A().lambda(B()) // a에서의 af + b에서의 bf

fun main() {
    f1 { aa, bb -> aa.af() + bb.bf() }
    f2 { af() + it.bf()}
}
```

### 80. 영역 함수

- 오로지 코드를 더 간결하고 일긱 좋게 만들기 위해 존재한다.
- let(), run(), with(), apply(), also()

### 81. 제네릭스 만들기

- 나중에 지정할 타입에 대해 작동하는 코드

##### Any

- 코틀린 클래스 계층의 루트다.
  - 접근방법: Any에 대해서만 연산을 수행하고, 다른 어느 타입도 요구하지 않는 경우

##### 제네릭스 정의하기

- 중복된 코드는 제네릭 함수나 타입으로 변환하는 것을 고려해볼만 하다.

```kotlin
fun <T> gFunction(arg: T): T = arg

class GClass<T>(val x: T) {
    fun f(): T = x
}
```

##### 타입 정보 보존하기

- 제네릭 클래스나 제네릭 함수의 내부 코드는 T 타입에 대해 알 수 없다 -> 타입 소거

```kotlin
class Car {
    override fun toString() = "Car"
}

class CarCrate(private val c: Car) {
    fun put(car: Car) {
        c = car
    }
    fun get(): Car = c
}

fun main() {
    val cc = CarCrate(Car())
    val car: Car = cc.get()
}

// T 타입의 값이 결과로 나오도록
open class Crate<T>(private val contents: T) {
    fun put(item: T) { contents = item }
    fun get(): T = contents
}

fun main() {
    val cc = Crate(Car())
    val car: Car = cc.get()
}
```

##### 타입 파라미터 제약

- 제네릭 타입 인자가 다른 클래스를 상속해야 한다.
  - 타입 파라미터 안에 선언된 함수나 프로퍼티에 접ㅂ근해야 할 때
  - 결과를 반환할 때 타입을 유지해야 할 때

```kotlin
interface Disposable {
    val name: String
    fun action(): String
}
fun <T: Disposable> nameOf(disposable: T) = disposable.name
```

##### 타입 소거

- 자바와의 호환성 이슈

##### 함수의 타입 인자에 대한 시체화

- 제네릭 함수를 호출할 때도 타입 정보가 소거 -> 함수 인자의 타입 정보를 보존하려면 reified 키워드 추가 필요
  - reified는 reified가 붙은 타입 인자의 타입 정보를 유지시키라고 컴파일러에 명령한다.

```kotlin
inline fun <reified T: Any> d() = a(T::class)
val kd = d<K>()
```

##### 타입 변성

- in T: T 타입 값을 인자로만 받고, T 타입 값을 반환하지는 않는다.
- out T: T 타입 값을 반환하기만 하고 T 타입 값을 인자로 받지는 않는다.

```kotlin
class Box<T> (private val contents: T) {
    fun put(item: T) { contents = item }
    fun get(): T = contents
}

class InBox<in T> (private val contents: T) {
    fun put(item: T) {contents=item}
}

class OutBox<out T>(private val contents: T) {
    fun get(): T = contents
}
```

### 82. 연산자 오버로딩

- 연산자 오버로딩을 사용하면, 새로 만든 타입에 대해 + 같은 연산자에 의미를 부여하거나 기존 타입에 대해 작용하는 연산자에 추가로 의미를 부여할 수 있다.

```kotlin
data class Num(val n: Int)
// 연산자 오버로딩시 operator 붙여주기
operator fun Num.plus(rval: Num) = Num(n + rval.n)
```

- 연산자에 특별한 의미를 부여하면 좋은 경우가 있다.

```kotlin
data class Molecule(
        val id: Int = idCount++,
        val attached: Molecule? = null
) {
    companion object {
        private var idCount = 0
    }
    operator fun plus(other: Molecule) {
        attached = other
    }
}
```

##### 동등성

- == (동등성)과 != (비동등성)은 equals() 멤버 함수를 호출한다. data 클래스는 자동으로 저장된 모든 필드를 서로 비교하는 equals()를 오버라이드 해준다.
- 널이 될 수 있는 객체를 ==로 비교하면 코틀린은 널 검사를 강제한다.

```kotlin
class E(var v: Int) {
    override fun equals(other: Any?) = when {
        this == other -> true
}
}

fun equalsWithIf(a: E?, b: E?) =
        if (a === null)
            b === null
        else
            a == b
```

##### 산술 연산자

- class E에 대해 기본 산술 연산자를 확장으로 정의 가능

```kotlin
// 단항 연산자
operator fun E.not() = this
operator fun E.unaryMinus() = E(-v)
operator fun E.unaryPlus() = E(v)
// 증가 연산자
operator fun E.inc() = E(v + 1)
operator fun E.dec() = E(v - 1)
```

##### 비교 연산자

- compareTo()를 정의하면 모든 비교 연산자를 쓸 수 있다.

```kotlin
operator fun E.compareTo(e: E): Int = v.compareTo(e.v)
```

##### 범위와 컨테이너

- rangeTo()는 범위를 생성하는 .. 연산자를 오버로드하고, contains()는 값이 범위 안에 들어가는지 여부를 알려주는 in 연산을 오버로드한다.

```kotlin
data class R(val r: IntRange) {
    override fun toString() = "R($r)"
}

fun main() {
    val a = E(2)
    val b = E(3)
    (a in r) eq true // a.rangeTo(b)
}
```

##### 컨테이너 원소 접근

- get()과 set()은 각괄호([])를 사용해 컨테이너의 원소를 읽고 쓰는 연산을 정의한다.

```kotlin
data class C(val c: MutableList<Int>) {
    override fun toString() = "C($c)"
}

operator fun C.contains(e: E) = e.v in c
operator fun C.get(i: Int): E = E(c[i])
```

##### 역작은 따옴표로 감싼 함 수 이름

- 코틀린은 함수 이름을 역작은따옴표로 감싸는 경우, 함수 이름에 공백, 몇몇 비표준 글자, 예약어 등을 사용하는 것을 허용한다.

```kotlin
fun `A long name with spaces`() = Unit
fun `*how* is this working?`() = Unit
```

### 83. 연산자 사용하기

- 실전에서 연산자를 오버로드하는 경우는 드물다. 연산자 오버로드는 보통 직접 라이브러리를 만들때만 사용한다.

```kotlin
fun main() {
    val list = MutableList(10) { 'a' + it }
    list[7] == 'h' // operator get()
    list.get(8) eq 'i'
}
```

##### 구조 분해 연산자

- 구조 분해에 사용되는 함수를 의미한다.
- component1(), component2()

```kotlin
class Duo(val x: Int, val y: Int) {
    operator fun component1(): Int {
        trace("component1()")
        return x
    }
}
```

### 84. 프로퍼티 위임

- 프로퍼티는 접근자 로직을 위임할 수 있다.
- by 키워드를 사용하면 프로퍼티를 위임과 연결할 수 있다.

### 85. 프로퍼티 위임 도구

- Map은 위임 프로퍼티의 위임 객체로 쓰일 수 있도록 미리 설정된 코틀린 표준 라이브러리 타입 중 하나다.

```kotlin
class Driver (
    map: MutableMap<String, Any?>
) {
    val name: String by map
    var age: Int by map
    var id: String by map
    var available: Boolean by map
    var coord: Pair<Double, Double> by map
}

fun main() {
    val info = mutableMapOf<String, Any?> (
        "name" to "Bruno Fiat",
        "age" to 22,
        "id" to "X97C111",
        "available" to false,
        "coord" to Pair(111.93, 1231.12)
    )
}
```
