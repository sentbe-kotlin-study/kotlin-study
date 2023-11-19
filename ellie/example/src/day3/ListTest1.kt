package day3
// Lists/Task3.kt
import atomictest.eq

fun checkAnagrams(s1: String, s2: String): Boolean {
    if(s1.length != s2.length) {
        return false
    }

    for(ch: Char in s1) {
        if(ch !in s2) {
            return false
        }
    }
    return true
}

fun main() {
    checkAnagrams("thing", "night") eq true
    checkAnagrams("leader", "dealer") eq true
    checkAnagrams("sector", "escort") eq true
    checkAnagrams("tablet", "battle") eq true
    checkAnagrams("friend", "finder") eq true
    checkAnagrams("senator", "treason") eq true
    checkAnagrams("terrain", "trainer") eq true
    checkAnagrams("teaching", "cheating") eq true

    checkAnagrams("pots", "stops") eq false
    checkAnagrams("escort", "sectors") eq false
    checkAnagrams("sections", "notices") eq false
}