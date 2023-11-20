package day4


fun main() {
    val strings = mutableListOf<String>()

    outer@ for(c in 'a'..'b') {
        for (i in 1..3) {
            if(i==2) continue@outer
            if("$c$i" == "b3") break@outer
            strings.add("$c$i")
        }
    }

    println(strings.toString())
}