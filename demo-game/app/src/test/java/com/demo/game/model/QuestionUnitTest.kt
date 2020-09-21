package com.demo.game.model

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class QuestionUnitTest {
    private lateinit var question: Question

    @Before
    fun setUp() {
        question = Question("Right", "Wrong")
    }

    @Test
    fun `ตอบถูก`() {
        question.answer("Right")
        assertTrue(question.isAnsweredCorrectly)
    }

    @Test
    fun `ตอบผิด`() {
        question.answer("Wrong")
        assertFalse(question.isAnsweredCorrectly)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `จะได้รับ exception เมื่อคำตอบไม่ตรงกับคำตอบที่มี`() {
        question.answer("Other")
    }

}