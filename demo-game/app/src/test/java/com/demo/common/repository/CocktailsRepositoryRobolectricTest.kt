package com.demo.common.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demo.DemoApplication
import com.demo.common.network.CocktailsApi
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CocktailsRepositoryRobolectricTest {

    @Test
    fun `xxxx`() {
        val api: CocktailsApi = mock()
        val app = getApplicationContext<DemoApplication>()
        val sharePreferences: SharedPreferences =
            app.getSharedPreferences("xxx", Context.MODE_PRIVATE);
        val repository: CocktailsRepository = CocktailsRepositoryImpl(api, sharePreferences)

        repository.saveHighScore(100)

        val score = repository.getHighScore()
        assertEquals(100, score)
    }
}