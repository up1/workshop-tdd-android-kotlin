package com.demo.game.model

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class GameUnitTests {
    @Test
    fun whenGettingNextQuestion_shouldReturnIt() {
        val question1 = Question("CORRECT", "INCORRECT")
        val questions = listOf(question1)
        val game = Game(questions)

        val nextQuestion = game.nextQuestion()

        assertSame(question1, nextQuestion)
    }
}