package com.demo.common.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.demo.DemoApplication
import com.demo.common.network.CocktailsApi
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CocktailsRepositoryIntegrationTest {

    @Test
    fun `fff`() {
        val api: CocktailsApi = mock()
        val appContext = getApplicationContext<DemoApplication>()
        val sharePreferences: SharedPreferences =
            appContext.getSharedPreferences("xxx", Context.MODE_PRIVATE);
        val repository: CocktailsRepository = CocktailsRepositoryImpl(api, sharePreferences)

        repository.saveHighScore(100)

        val score = repository.getHighScore()
        assertEquals(100, score)
    }


}