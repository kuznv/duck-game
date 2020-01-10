package ru.ducks.duckone

import ru.ducks.duckone.api.IUpdate
import ru.ducks.duckone.api.IPlayer
import ru.ducks.duckone.board.Point
import ru.ducks.duckone.board.Cell

data class DuckUpdate(val player: IPlayer, val point: Point, val cell: Cell) : IUpdate