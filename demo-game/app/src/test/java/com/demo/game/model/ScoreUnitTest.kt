package com.demo.game.model

import org.junit.Assert.*
import org.junit.Test

class ScoreUnitTest {

    @Test
    fun `ค่า high score และ current เริ่มต้น`() {
        val score = Score()
        assertEquals(0, score.highest)
        assertEquals(0, score.current)
    }

    @Test
    fun `ค่า high score ไม่เปลี่ยนเมื่อ current เพิ่มขึ้นแต่มีค่าน้อยกว่า`() {
        val score = Score(2)
        score.increment()
        assertEquals(2, score.highest)
        assertEquals(1, score.current)
    }

    @Test
    fun `ค่า high score เปลี่ยนเมื่อ current เพิ่มขึ้นและมากกว่า`() {
        val score = Score(2)
        score.increment()
        score.increment()
        score.increment()
        assertEquals(3, score.highest)
        assertEquals(3, score.current)
    }

}