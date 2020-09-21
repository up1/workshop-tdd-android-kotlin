package com.demo.common.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.test.platform.app.InstrumentationRegistry
import com.demo.common.network.CocktailsApi
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock

class CocktailsRepositoryIntegrationTest {

    @Test
    fun save_and_get_score_from_sharedpreference() {
        val api: CocktailsApi = mock()
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val sharedPreferences = appContext.getSharedPreferences(
            "TEST", Context.MODE_PRIVATE)
        val repository = CocktailsRepositoryImpl(api, sharedPreferences)
        val score = 100
        repository.saveHighScore(score)

        val actualScore = repository.getHighScore()

        assertEquals(score, actualScore)
    }
}