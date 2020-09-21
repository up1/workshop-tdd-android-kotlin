package com.demo.game.model

import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Test

class GameUnitTest {

    @Test
    fun `ตอบผิด`() {
        val question1 = Question("Right", "Wrong")
        val questions = listOf(question1)

        val stubQuestion = mock<Question>()
        whenever(stubQuestion.answer(any())).thenReturn(false)

        val spyScore = mock<Score>()

        val game = Game(questions, spyScore)
        game.answer(stubQuestion, "Wrong")

        verify(spyScore, never()).increment()
    }

    @Test
    fun `ตอบถูก score ต้องเพิ่ม2`() {
        val question1 = Question("Right", "Wrong")
        val questions = listOf(question1)

        val stubQuestion = mock<Question>()
        whenever(stubQuestion.answer("Right")).thenReturn(true)

        val spyScore = mock<Score>()

        val game = Game(questions, spyScore)
        game.answer(stubQuestion, "Right")

        verify(spyScore, times(1)).increment()
    }

    @Test
    fun `ตอบถูก score ต้องเพิ่ม`() {
        val question1 = Question("Right", "Wrong")
        val questions = listOf(question1)
        val spyScore = mock<Score> {  }
        val game = Game(questions, spyScore)
        game.answer(question1, "Right")
        verify(spyScore, times(1)).increment()
    }

    @Test
    fun `ข้อมูลของ question ที่ 1`() {
        val question1 = Question("Right", "Wrong")
        val questions = listOf(question1)

        val game = Game(questions)
        val actualQuestion = game.nextQuestion()
        assertSame(question1, actualQuestion)
    }

    @Test
    fun `ไม่มีข้อมูลของ question แล้ว`() {
        val question1 = Question("Right", "Wrong")
        val questions = listOf(question1)

        val game = Game(questions)
        game.nextQuestion()
        val actualQuestion = game.nextQuestion()
        assertNull(actualQuestion)
    }
}