package com.demo

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityRobolectricTest{
    @Test
    fun start_new_game() {
        ActivityScenario.launch(MainActivity::class.java)
    }
}