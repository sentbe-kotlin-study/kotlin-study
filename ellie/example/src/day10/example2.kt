package day10
// DelegationTools/DelegToolsSoln1.kt
import kotlin.properties.Delegates
import atomictest.*

data class Flag(val b: Boolean = false)

fun main() {
    // TODO
    // 변수 d, s, f 를 프로퍼티 위임과 함께 선언하세요
    var d : Double by Delegates.notNull()
    var s : String by Delegates.notNull<String>()
    var f : Flag by Delegates.notNull()

    capture {
        d
    } eq "IllegalStateException: Property " +
            "d should be initialized before get."
    capture {
        s
    } eq "IllegalStateException: Property " +
            "s should be initialized before get."
    capture {
        f
    } eq "IllegalStateException: Property " +
            "f should be initialized before get."
    d = 1.1
    s = "yes"
    f = Flag(true)
    d eq 1.1
    s eq "yes"
    f eq "Flag(b=true)"
}