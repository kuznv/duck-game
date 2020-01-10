package ru.ducks.duckone.api

interface IGameSession<S: IGameState> {
    val state: S?
}