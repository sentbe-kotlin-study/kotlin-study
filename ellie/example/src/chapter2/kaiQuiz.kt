package chapter2
fun displayContent(s : String) {
    for(a:Char in s) {
        println(a)
    }
}

fun sumOfEven(n: Int): Int {
    var sum = 0
    for(i in 0..n step 2) {
        sum += i
    }
    return sum
}


class Robot {
    val xLimit = 100;
    val yLimit = 100;
    var x = 0
    var y = 0

    fun right(steps: Int) { // 멍청잼
        x = makePositive(x)
        if(steps + x > xLimit) {
            x = steps + x - xLimit
        } else {
            x += steps
        }
    }

    fun left(steps: Int) {
        x = makePositive(x)
        if(x - steps < 0) {
            x =  steps - x - xLimit
        } else {
            x -= steps
        }
    }

    fun down(steps: Int) {
        y = makePositive(y)
        if(steps + y > yLimit) {
            y = steps + y - yLimit
        } else {
            y += steps
        }
    }

    fun up(steps: Int) {
        y = makePositive(y)
        if(y - steps < 0) {
            y = steps - y - yLimit
        } else {
            y -= steps
        }
    }
    fun makePositive(n: Int): Int {
        if( n < 0 ) {
            return -n
        } else {
            return n
        }
    }
    fun getLocation(): String = "($x, $y)"
}

class Floating(private val d:Double) {
    override fun toString(): String {
        return d.toString()
    }
}

fun main() {
    displayContent("abcdef")
    println(sumOfEven(10))  // 30

    /////////////////////////////////
    val robot = Robot()
    println(robot.getLocation())
    robot.up(1)
    println(robot.getLocation())
    robot.left(10)
    println(robot.getLocation())
    robot.right(1)
    println(robot.getLocation())
    robot.down(20)
    println(robot.getLocation())
    /////////////////////////////////

    var f = Floating(1.23)
    println(f.toString())
}