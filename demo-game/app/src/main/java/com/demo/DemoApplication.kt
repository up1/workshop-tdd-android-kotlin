package com.demo

import android.app.Application
import android.content.Context
import com.demo.common.network.CocktailsApi
import com.demo.common.repository.CocktailsRepository
import com.demo.common.repository.CocktailsRepositoryImpl
import com.demo.game.factory.CocktailsGameFactory
import com.demo.game.factory.CocktailsGameFactoryImpl

class DemoApplication : Application() {
    val repository: CocktailsRepository by lazy {
        CocktailsRepositoryImpl(
            CocktailsApi.create(),
            getSharedPreferences("Cocktails", Context.MODE_PRIVATE)
        )
    }

    val gameFactory: CocktailsGameFactory by lazy {
        CocktailsGameFactoryImpl(repository)
    }
}