package com.demo.game.model

class Score(highestScore: Int = 0) {
    var current = 0
        private set

    var highest = highestScore
        private set

    fun increment() {
        current++
        if (current > highest) {
            highest = current
        }
    }
}
