package com.demo.common.repository

import android.content.SharedPreferences
import com.demo.common.network.CocktailsApi
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CocktailsRepositoryUnitTest {

    @Mock
    private lateinit var api: CocktailsApi

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Test
    fun `save score to sharedpreferences`() {
        whenever(sharedPreferences.edit())
            .thenReturn(sharedPreferencesEditor)

        val repository = CocktailsRepositoryImpl(api, sharedPreferences)

        val score = 10
        repository.saveHighScore(score)

        inOrder(sharedPreferencesEditor) {
            verify(sharedPreferencesEditor).putInt(any(), eq(score))
            verify(sharedPreferencesEditor).apply()
        }
    }

    @Test
    fun `get score from sharedpreferences`() {
        val repository = CocktailsRepositoryImpl(api, sharedPreferences)
        repository.getHighScore()

        verify(sharedPreferences).getInt(any(), any())
    }

}