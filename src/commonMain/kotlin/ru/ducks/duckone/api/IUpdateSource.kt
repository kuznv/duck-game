package ru.ducks.duckone.api

interface IUpdateSource<U: IUpdate> {
    suspend fun getUpdate(): U
}