# 7. Power Tool

### 79. 확장 람다
- 확장 람다는 확장 함수와 비슷하다. 차이가 있다면 함수가 아니라 람다라는 점이다.

```kotlin

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