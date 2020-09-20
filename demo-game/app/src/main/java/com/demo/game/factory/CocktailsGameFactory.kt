package com.demo.game.factory

import com.demo.game.model.Game


interface CocktailsGameFactory {

    fun buildGame(callback: Callback)

    interface Callback {
        fun onSuccess(game: Game)
        fun onError()
    }
}