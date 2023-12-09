package day7

import atomictest.*

private fun f() = trace("f() $p")
private val p = "p"

object Space {
  /*TODO*/
  fun f() = trace("Space.f() $p")
  private val p = "Space.p"
}

private object Space2 { // object 가 선언된 현재의 파일 안에서 만 사용
  /*TODO*/
  fun f() = trace("Space2.f() $p")
  private val p = "Space2.p"
}

fun main() {
  f()
  Space.f()
  Space2.f()
  trace eq """
    f() p
    Space.f() Space.p
    Space2.f() Space2.p
  """
}