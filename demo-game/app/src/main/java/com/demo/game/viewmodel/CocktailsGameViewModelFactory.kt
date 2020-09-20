package com.demo.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.common.repository.CocktailsRepository
import com.demo.game.factory.CocktailsGameFactory

class CocktailsGameViewModelFactory(
    private val repository: CocktailsRepository,
    private val gameFactory: CocktailsGameFactory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CocktailsGameViewModel(repository, gameFactory) as T
    }
}