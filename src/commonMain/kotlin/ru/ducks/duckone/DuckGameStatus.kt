package ru.ducks.duckone

import ru.ducks.duckone.api.IPlayer

sealed class DuckGameStatus {
    object ACTIVE : DuckGameStatus()
    object DRAW : DuckGameStatus()
    data class VICTORY(val player: IPlayer) : DuckGameStatus()
}