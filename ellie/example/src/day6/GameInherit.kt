package day6

import atomictest.*

open class Game {
    val initOrder = mutableListOf<String>()
    init {
        initOrder += "Game"
    }
}

open class BoardGame: Game() {
    init {
        initOrder += "BoardGame"
    }
}

class Chess: BoardGame() {
    init {
        initOrder += "Chess"
    }
}

fun main() {
    Chess().initOrder eq "[Game, BoardGame, Chess]"
}