package com.demo.game.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.demo.common.repository.CocktailsRepository
import com.demo.game.factory.CocktailsGameFactory
import com.demo.game.model.Game
import com.demo.game.model.Question
import com.demo.game.model.Score
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer

class CocktailsGameViewModelUnitTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory
    private lateinit var viewModel: CocktailsGameViewModel
    private lateinit var game: Game
    private lateinit var loadingObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<Boolean>
    private lateinit var scoreObserver: Observer<Score>
    private lateinit var questionObserver: Observer<Question>
    private lateinit var gameOverObserver: Observer<Boolean>

    @Before
    fun setup() {
        repository = mock()
        factory = mock()
        viewModel = CocktailsGameViewModel(repository, factory)

        game = mock()

        loadingObserver = mock()
        errorObserver = mock()
        scoreObserver = mock()
        questionObserver = mock()
        gameOverObserver = mock()
        viewModel.getLoading().observeForever(loadingObserver)
        viewModel.getScore().observeForever(scoreObserver)
        viewModel.getQuestion().observeForever(questionObserver)
        viewModel.getError().observeForever(errorObserver)
    }

    @Test
    fun init_shouldBuildGame() {
        viewModel.initGame()

        verify(factory).buildGame(any())
    }

    @Test
    fun init_shouldShowLoading() {
        viewModel.initGame()

        verify(loadingObserver).onChanged(eq(true))
    }

    @Test
    fun init_shouldHideError_whenFactoryReturnsSuccess() {
        setUpFactoryWithSuccessGame(game)

        viewModel.initGame()

        verify(errorObserver, times(2)).onChanged(eq(false))
    }


    private fun setUpFactoryWithSuccessGame(game: Game) {
        doAnswer {
            val callback: CocktailsGameFactory.Callback = it.getArgument(0)
            callback.onSuccess(game)
        }.whenever(factory).buildGame(any())
    }

    @Test
    fun init_shouldHideLoading_whenFactoryReturnsError() {
        setUpFactoryWithError()

        viewModel.initGame()

        verify(loadingObserver).onChanged(eq(false))
    }

    private fun setUpFactoryWithError() {
        doAnswer {
            val callback: CocktailsGameFactory.Callback = it.getArgument(0)
            callback.onError()
        }.whenever(factory).buildGame(any())
    }

}