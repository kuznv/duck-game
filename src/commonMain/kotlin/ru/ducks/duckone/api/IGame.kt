package ru.ducks.duckone.api

interface IGame<S: IGameState, U: IUpdate> {

    fun applyUpdate(gameState: S, update: U): S
}
