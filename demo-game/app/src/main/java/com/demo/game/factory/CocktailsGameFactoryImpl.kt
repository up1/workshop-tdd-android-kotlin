package com.demo.game.factory

import com.demo.common.network.Cocktail
import com.demo.common.repository.CocktailsRepository
import com.demo.common.repository.RepositoryCallback
import com.demo.game.factory.CocktailsGameFactory.Callback
import com.demo.game.model.Game
import com.demo.game.model.Question
import com.demo.game.model.Score

class CocktailsGameFactoryImpl(
    private val repository: CocktailsRepository
) : CocktailsGameFactory {

    override fun buildGame(callback: Callback) {
        repository.getAlcoholic(
            object : RepositoryCallback<List<Cocktail>, String> {
                override fun onSuccess(cocktailList: List<Cocktail>) {
                    val questions = buildQuestions(cocktailList)
                    val score = Score(repository.getHighScore())
                    val game = Game(questions, score)
                    callback.onSuccess(game)
                }

                override fun onError(e: String) {
                    callback.onError()
                }
            })
    }

    private fun buildQuestions(cocktailList: List<Cocktail>) = cocktailList.map { cocktail ->
        val otherCocktail = cocktailList.shuffled().first { it != cocktail }
        Question(cocktail.strDrink, otherCocktail.strDrink, cocktail.strDrinkThumb)
    }
}