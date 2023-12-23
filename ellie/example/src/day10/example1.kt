package day10;

import kotlin.reflect.KProperty;

class OnlyPositive {
  private var realValue: Int = 0
  
  operator fun getValue(thisRef: Any?, property:KProperty<*>): Int {
    return realValue
  }
  
  // TODO : setter 를 만드세요
  operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
    realValue = if (value > 0) value else 0
  }
  
}

class A {
  var number: Int by OnlyPositive()
}

fun main(args: Array<String>) {
  val a = A()
  a.number = -50        // OnlyPositive.setValue() 가 호출되고 0이 저장
  println(a.number)
  
  a.number = 100
  println(a.number)
}