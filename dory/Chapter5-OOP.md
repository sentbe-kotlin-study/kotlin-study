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

### 57. 부생성자

- 오버로드한 생성자를 <b>부생성자</b>라고 한다.

```kotlin
class WithSecondary(i: Int) {
    // 주생성자
    init {
        trace("Primary: $i")
    }
    // 부생성자
    constructor(c: Char): this(c-'A') {
        trace("Secondary: '$c'")
    }
  // 부생성자2
    constructor(s: String){
      this(s.first())
      trace("Secondary: '$c'")
    }
}
```

- 주생성자는 언제나 부생성자에 의해 직접 호출되거나 다른 부생성자 호출을 통해 간접적으로 호출되어야 한다.

### 58. 상속

- 상속: 기존 클래스를 재사용하면서 변경해 새로운 클래스를 만드는 매커니즘

```kotlin
// 상속가능 : open
open class Parent

clas Child : Parent()

// 상속 불가
final class Single
class AnotherSingle
```

- 함수 오버라이드하기

```kotlin
open class GreatApe {
  protected val energy = 0 // 외부 세계에 대해 닫혀 있고, 하위 클래스에서만 접근이나 오버라이드 가능
  open fun call() = "Hoo!"
  open fun eat() {
    energy += 10
  }
  fun climb(x: Int) {
    energy -= x
  }
  fun energyLevel() = "Energy: $energy"
}

class Bonobo: GreatApe() {
    override fun call() = "Eep!"
    override fun eat() {
        // 부모 클래스의 프로퍼티를 변경한다.
        energy += 10
        // 부모 클래스의 함수를 호출한다
        super.eat()
    }
    // 함수를 호출한다.
    fun run() = "Bonobo run"
}
```

### 59. 기반 클래스 초기화

- 부모 클래스에 생성자 파라미터가 있다면, 자식 클래스가 생성되는 동안 반드시 부모 클래스의 생성자 인자를 제공해야 한다.
- 자식 클래스는 부모 클래스의 부생성자를 호출할 수도 있다.

```kotlin
open  class GreatApe (
        val weight: Double,
        val age: Int
)

open class Bonobo(weight: Double, age: Int):
    GreatApe(weight, age)
```

### 60. 추상 클래스

- 클래스 멤버에서 본문이나 초기화를 제거하려면 abstract 변경자를 해당 멤버 앞에 붙여야 한다.

```kotlin
abstract class WithProperty {
    abstract val x: Int
}
```

- 인터페이스와 추상 클래스 차이점
  - 추상 클래스에는 상태가 있으나, 인터페이스에는 상태가 없다.
  - 상태: 프로퍼티 안에 저장된 데이터를 의미한다.

### 61. 업캐스트

- 업캐스트: 객체 참조를 받아서 그 객체의 기반 타입에 대한 참조처럼 취급하는 것

```kotlin
interface Shape {
    fun draw(): String
    fun erase(): String
}

class Circle: Shape {
    override fun draw(): String {TODO("Not yet implemented") }
    override fun erase(): String {TODO("Not yet implemented")}
}

class Square: Shape {
  override fun draw(): String {TODO("Not yet implemented") }
  override fun erase(): String {TODO("Not yet implemented")}
}

fun show(shape: Shape) {
    trace("")
}

fun main() {
    listOf(Circle(), Square()).forEach(::show) // 각 타입은 모두 shape 클래스의 객체처럼 취급 -> 업캐스트
}
```

<b>실제로 업캐스트를 사용하지 않는데 상속을 사용하는 거의 모든 경우는 상속을 잘못 사용하는 것이다!</b>

### 62. 다형성

- 다형성: 부모 클래스 참조가 자식 클래스의 인스턴스를 가리키는 경우 발생한다.

```kotlin
import kotlin.contracts.contract

abstract class Character(val name: String) {
    abstract fun play(): String
}

interface Fighter {
    fun fight() = "Fight"
}

interface Magician {
    fun doMagic() = "Magic!"
}

class Warrior :
    Character("Warrior"), Fighter {
        override fun play() = fight()
    }

open class Elf(name: String = "Elf"):
        Character(name), Magician {
            override fun play() = doMagic()
}

class FightingElf :
        Elf("FightingElf"), Fighter {
            override fun play() = super.play() + fight()
        }

fun Character.playTurn() =
        trace(name + ":" + play())

fun main() {
    val characters: List<Character> = listOf(
            Warrior(), Elf(), FightingElf()
    )
  characters.forEach { it.playTurn() }
  trace eq """
    Warrior: Fight!
    Elf: Magic!
    FightingElf: Magic!Fight!
  """
}
```

### 63. 합성

- 기좆 클래스의 객체를 새 클래스 안에 생성하는 좀 더 직접적인 접근 방법을 택할 수도 있다. -> 합성
- 합성을 쓸 경우는 기본 코드의 기능을 재사용하는 것이다.

```kotlin
// 상속: 집은 건물이다
// 합성: 부엌을 포함한다.
interface House: Building {
    val kitchen: Kitchen
}
```

### 64. 상속과 확장

- 기존 클래스를 새로운 목적으로 활용하기 위해 새로운 함수를 추가해야 할 때가 있다.
- 이때 기존 클래스를 변경할 수 없으면 새 함수를 추가하기 위해 상속을 사용해야 한다.
- 이로 인해 코드를 이해하고 유지 보수하기 어려워진다.

```kotlin
open class Heater {
    fun heat(temperature: Int) = "heating to $temperature"
}

fun warm(heater: Heater) {
    heater.heat(70) eq "heating to 70"
}

class HVAC : Heater() {
    fun cool(temperature: Int) = "cooling to $temperature"
}
```

### 65. 클래스 위임

- 클래스 위임은 상속과 합성의 중간 지점이다.
  - 새 클래스 안에 멤버 객체를 심고, 상속과 마찬가지로 심겨진 하위 객체의 인터페이스를 노출시킨다.

```kotlin
// 클래스 위임
interface AI
class A: AI

class B(val a: A) : AI by a
```

- 클래스 위임을 사용해 다중 클래스 상속을 흉내낼 수 있다.

### 66. 다운캐스트

- 다운 캐스트는 이전에 업캐스트 했던 객체의 구체적인 타입을 발견한다.
- 다운 캐스트는 실행 시점에 일어나며 실행 시점 타입 식별이라고도 한다.

### 67. 봉인된 클래스 
- sealed 키워드로 상속을 제한한 클래스를 봉인된 클래스라고 부른다.
  - sealed 클래스를 직접 상속한 하위 클래스는 반드시 기반 클래스와 같은 패키지와 모듈 안에 있어야 한다.
  - sealed 클래스는 tram 같은 새 하위 클래스를 도입했을 때 변경해야 하는 모든 지점을 표시해준다.

```kotlin
sealed class Transport 

data class Train(
        val line: String
) : Transport()

data class Bus(
        val number: String,
        val capacity: Int
) : Transport()

// else가 필요없어짐 -> sealed가 관리하기 때문에
fun travel(transport: Transport) = 
        when (transport) {
            is Train -> 
                "Train ${transport.line}"
            is Bus -> 
                "Bus ${transport.number}: " +
                        "size ${transport.capacity}"
        }

fun main() {
    listOf(Train("S1"), Bus("11", 90))
            .map(::travel) eq "[Train S1, Bus 11: size 90]"
}
```

- sealed 클래스는 기본적으로 하위 클래스가 모두 같은 파일 안에 정의되어야 한다는 제약이 가해진 abstract 클래스다.
- 그러나 직접적 상속이 아니면 다른 파일에 있을 수도 있음! 

##### 하위 클래스 열거하기
- Top의 직접적인 하위클래스만 나타남!
```kotlin
sealed class Top
class Middle1 : Top()
class Middle2 : Top()
open class Middle3 : Top()
class Bottom3 : Middle3()

fun main() {
    Top::class.sealedSubclasses
            .map { it.simpleName } eq
            "[Middle1, Middle2, Middle3]"
}
```

### 68. 타입 검사
> 코틀린에서는 객체 타입에 기반해 원하는 동작을 쉽게 수행할 수 있다. 일반적으로 이런 타입에 따른 동작은 다형성의 영역에 속하므로 타입 검사를 통해 흥미로운 설계를 할 수 있다.

```kotlin
// 타입 체킹을 위해 when 식 추가 -> 그러나 타입이 늘어나게 되면 코드가 점점 지저분해진다!
fun Insect.water() = 
        when (this) {
          is SwimmingInsect -> swim()
          is WalterWalker -> walkWater()
          else -> "$name: drown"
        }
```

###### 외부 함수에서 타입 검사하기

```kotlin
interface BeverageContainer {
    fun open(): String
    fun pour(): String
}

fun BeverageContainer.recycle() =
        when(this) {
            is Can -> "Recycle"
            is GlassBottle -> "Recycle glass"
            else -> "Landfill"
        }
```

- 이 설계의 문제점
  - 새 타입 추가시 else 절 사용 -> 꼭 수정해야 하는 함수를 수정하지 않는 경우 
  - 컴파일러가 recycle() 같은 함수에서 타입 검사를 추가하지 않았음을 알려줄 필요가 있다! -> sealed 클래스 사용

```kotlin
sealed class Shape {
    fun draw() = "$name: Draw"
}

class Circle : Shape()

class Square : Shape() {
    fun rotate() = "Square: Rotate"
}

,,,

// else 필요없음 
fun turn(s: Shape) = when(s){
    is Circle -> ""
    is Square -> ""
}
```

- 하지만 sealed 클래스를 사용할 경우 -> 상속의 상속을 하는 .. 자손 클래스는 알수가 없음 ㅠㅠ 
- when절 안에 또 when 절,,

```kotlin
interface BeverageContainer {
    fun recycle(): String // 여기 안에 넣어요~!!
}
```

### 69. 내포된 클래스
- 내포된 클래스는 클래스 안의 클래스!

```kotlin
// plane은 Airport 안 클래스이다!
class Airport(private val code: String) {
    open class Plane {
        fun contact(airport: Airport) = "Contacting ${airport.code}"
    }
    private class PrivatePlane : Plane()
    fun privatePlane(): Plane = PrivatePlane()
}
```

- 일반 클래스는 다른 클래스의 private 프로퍼티에 접근할 수 없다.
  - PrivatePlane은 Airport 밖에서는 절대로 볼 수 없다는 뜻!

```kotlin
fun main() {
    val denver = Airport("DEN")
    var plane = Plane()
    plane.contact(denver)
    // 밑에는 불가임
    val p = plane as PrivatePlane
}
```

##### 지역 클래스
- 함수 안에 내포된 클래스를 지역 클래스라고 한다.

```kotlin
fun localClasses() { 
    open class Amphibian 
    class Frog : Amphibian()
    val amphibian: Amphibian = Frog()
}

fun createAmphibian() : Amphbian {
    val Frog : Amphibian
    return Frog()
}
```
##### 인터페이스에 포함된 클래스

```kotlin
import java.lang.reflect.Type

interface Item {
  val type: Type
  data class Type(val type: String)
}

class Bolt(type: String) : Item {
    override val type = Item.Type(type)
}
```

##### 내포된 이넘
- 이넘도 클래스이므로 다른 클래스 안에 내포될 수 있다
```kotlin
class Ticket(
        val name: String,
        val seat: Seat = Coach
) {
    enum class Seat {
        Coach,
        Premium,
        Business,
        First
    }
}

fun upgrade(): Ticket {
    val newSeat = values()[
            (seat.ordinal +1)
                    .coerceAtMost(First.ordinal) //  호출된 객체가 특정 객체보다 작은지 
    ]
    return Ticket(name, newSeat)
}
```

### 70. 객체
- object는 논리적으로 한 개체 안에 속한 함수와 프로퍼티를 함께 엮는 방법이다.
  - object의 인스턴스를 직접 생성하는 경우느 결코 없다.
  - object를 정의하면 그 object의 인스턴스가 오직 하나만 생긴다.

```kotlin
object JustOne {
    val n = 2
    fun f() = n * 10
    fun g() = this.n * 20 
}

//- JustOne()을 사용해 새 인스턴스를 만들 수 없다. 
// object 키워드가 구조 정의와 동시에 객체를 생성해버리기 때문이다.
fun main() {
   // val x = JustOne() 오류남
    JustOne.n == 2
}
```

```kotlin
// 인터페이스 상속도 가능!
interface PaintPreparation {
    fun prepare(): String
}

object Prepare: PaintPreparation {
    override fun prepare() = "Scrape"
}
```

### 71. 내부 클래스
- 내부 클래스는 내포된 클래스와 비슷하지만, 내부 클래스의 객체는 자신을 둘러싼 클래스의 인스턴스에 대한 참조를 유지한다.
```kotlin
class Hotel(private val reception: String) {
    open inner class Room(val id: Int=0) {
        fun callReception() = 
                "Room $id Calling $reception" // 호텔의 reception 바로 사용 
    }
  private inner class Closet : Room() // 내포된 클래스는 이너 클래스 상속 불가 
  fun closet(): Room = Closet()
}
```

##### 한정된 this
- 한정된 this : this 뒤에 @ 붙이고 대상 클래스 이름을 덧붙인 것 
```kotlin
class Fruit {
    fun changeColor(color: String) = "Fruit $color"
  inner class Seed {
    fun changeColor(color: String) = "Seed $color"
    fun whichThis() {
        this.name eq "Seed"
        this@Seed.name eq "Seed"
        this@Fruit.name eq "Fruit"
    }
  }
}
```

##### 내부 클래스 상속
- 내부 클래스는 다른 외부 클래스에 있는 내부 클래스를 상속할 수 있다.
```kotlin
open class Egg {
    private var yolk = Yolk()
    open inner class Yolk {
        init { trace("Egg.Yolk()") }
        open fun f() { trace("Egg.Yolk.f()") }
    }
}

class BigEgg : Egg() {
    inner class Yolk : Egg.Yolk() { // 내부 클래스를 상속!!
        init {
            trace("BigEgg.Yolk()")
        }
    }
}
```

##### 지역 내부 클래스와 익명 내부 클래스
- 멤버 함수 안에 정의된 클래스를 지역 내부 클래스라고 한다.
- 이런 클래스를 객체 식이나 SAM 변환을 사용해 익명으로 생성할 수도 있다.

```kotlin
fun interface Pet {
    fun speak(): String
}

object CreatePet {
    fun home() = "home!"
    fun dog(): Pet {}
        val say = "Bark"
        // 지역 내부 클래스
        class Dog : Pet {
            override fun speak() = say + home()
        }
    return Dog()
}

fun cat() : Pet {
    val emit = "Meow"
    // 익명 내부 클래스
    return object: Pet {
        override fun speak() = emit + home()
    }
}
```


### 72. 동반 객체
- companion object 안에 있는 함수와 필드는 클래스에 대한 함수와 필드다.
  - 클래스 안에서 동반 객체를 정의하면 클래스의 내부에서 동반 객체 원소를 투명하게 참조 가능!
```kotlin
class WithCompanion {
    companion object {
        val i = 3
        fun f() = i * 3
    }
    fun g() = i + f()
}
```
- 동반 객체는 클래스 당 하나만 생성이 가능하다!

```kotlin
clas WithNamed {
    companion objet Named { // 이름 설정 가능 
        fun s() = "from named"
    }
}
```