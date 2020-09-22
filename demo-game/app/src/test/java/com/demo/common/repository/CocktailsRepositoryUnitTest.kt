package com.demo.common.repository

import android.content.SharedPreferences
import com.demo.common.network.CocktailsApi
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Before
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

    @Before
    fun setup() {
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
    }

    @Test
    fun `success with save new high score`() {
        val api: CocktailsApi = mock()
        val repository = CocktailsRepositoryImpl(api, sharedPreferences)
        repository.saveHighScore(100)
        verify(sharedPreferencesEditor).putInt(any(), eq(100))
        verify(sharedPreferencesEditor).apply()
    }

    @Test
    fun `not save high score when score less than high score`() {
        whenever(sharedPreferences.getInt(any(), any())).thenReturn(200)

        val api: CocktailsApi = mock()
        val repository = CocktailsRepositoryImpl(api, sharedPreferences)
        repository.saveHighScore(100)
        verify(sharedPreferencesEditor, never()).putInt(any(), eq(100))
        verify(sharedPreferencesEditor, never()).apply()
    }

}