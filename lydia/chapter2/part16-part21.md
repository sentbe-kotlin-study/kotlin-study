#### 챕터2. 객체 소개
- 코틀린은 자바와 같은 객체지향 언어와 함수형 언어의 장점을 모두 가지고 있다.


## 16. 객체는 모든 곳에 존재한다
- 객체는 프로퍼티(val과 var)를 사용해 데이터를 저장하고, 함수를 사용해 이런 데이터에 대한 연산을 수행한다.
```kotlin
fun main() {
    val r1 = IntRange(0, 10)
    val r2 = 5..7
    println(r1)
    println(r2)
}
```
- 위 예제는 IntRange 클래스에 속하는 두 객체(인스턴스)를 만든다.
  - 각 객체는 메모리에 자신만의 저장공간을 가지고 있다.
  - IntRange 는 클래스지만, 0부터 10까지의 범위를 가리키는 r1 범위는 r2 와 구분되는 별도의 객체다. 


***


## 17. 클래스 만들기
- IntRange 나 String 처럼 미리 정의된 타입을 사용할 수 있을 뿐만 아니라, 직접 원하는 객체의 타입을 정의할 수도 있다.
  - 멤버 함수: 클래스에 속한 함수
  - 최상위(top-level) 함수: 클래스에 속하지 않은 함수
```kotlin
class Dog {
    fun bark() {
        println("Yip!")
    }
}
fun main() {
    val dog = Dog()
}
```
- main() 에서는 Dog 객체를 만들어서 dog 이라는 val 에 대입한다.
  - dog을 사용하지 않으면 경고가 표시된다.
```kotlin
class Cat {
    fun meow() = "mrrrow!"
}
fun main() {
    val cat = Cat()
    val m1 = cat.meow()
    println(m1)
    // mrrrow!
}
```
- 객체 내에서는 this 를 사용해 자신의 프로퍼티와 멤버 함수에 접근할 수 있다.
  - this 를 반드시 적어야 하거나 this 를 적는 스타일을 권장하는 프로그래머도 있다.
  - 코틀린에서는 this 를 생략할 수 있고, 불필요한 this는 사용하지 않는 것이 좋다.


***

