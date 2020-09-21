package com.demo.day01

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.locale.LocaleTestRule

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val localeTestRule = LocaleTestRule()

    @Test
    fun mainActivityTest() {
        ActivityScenario.launch(MainActivity::class.java)
        Screengrab.screenshot("step_01")
        onView(withId(R.id.hello))
            .check(matches(withText("Hello World!")))
        Screengrab.screenshot("step_02")
    }
}
