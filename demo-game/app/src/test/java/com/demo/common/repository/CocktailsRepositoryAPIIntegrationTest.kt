package com.demo.common.repository

import android.content.SharedPreferences
import com.demo.common.network.Cocktail
import com.demo.common.network.CocktailsApi
import com.nhaarman.mockitokotlin2.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CocktailsRepositoryAPIIntegrationTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Test
    fun `Get all questions from API`() {
        mockWebServer.enqueue(MockResponse()
            .setBody("{}")
            .setResponseCode(200)
        )
        val sharedPreferences: SharedPreferences = mock()
        val api = retrofit.create(CocktailsApi::class.java)
        val repository = CocktailsRepositoryImpl(api, sharedPreferences)

        val callback: RepositoryCallback<List<Cocktail>, String> = mock()
        repository.getAlcoholic(callback)
        verify(callback, timeout(100)).onSuccess(any())
    }

    @Test
    fun `500 Get all questions from API`() {
        mockWebServer.enqueue(MockResponse()
            .setBody("{}")
            .setResponseCode(500)
        )
        val sharedPreferences: SharedPreferences = mock()
        val api = retrofit.create(CocktailsApi::class.java)
        val repository = CocktailsRepositoryImpl(api, sharedPreferences)

        val callback: RepositoryCallback<List<Cocktail>, String> = mock()
        repository.getAlcoholic(callback)
        verify(callback, timeout(100)).onError(any())
    }

    @Test
    fun `Failure Get all questions from API`() {
        mockWebServer.enqueue(
            MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START)
        )
        val sharedPreferences: SharedPreferences = mock()
        val api = retrofit.create(CocktailsApi::class.java)
        val repository = CocktailsRepositoryImpl(api, sharedPreferences)

        val callback: RepositoryCallback<List<Cocktail>, String> = mock()
        repository.getAlcoholic(callback)
        verify(callback, timeout(100)).onError(eq("on failure"))
    }



}