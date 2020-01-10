package ru.ducks.duckone.internal

import ru.ducks.duckone.DuckGameState
import ru.ducks.duckone.DuckUpdate
import ru.ducks.duckone.api.CellNotEmptyException
import ru.ducks.duckone.api.IllegalSquareTypeException
import ru.ducks.duckone.api.NotACurrentPlayerException
import ru.ducks.duckone.api.PointIsOutOfGameBoardException
import ru.ducks.duckone.board.Cell
import ru.ducks.duckone.board.contains
import ru.ducks.duckone.board.get

internal object DuckGameUpdateValidator {

    fun validateUpdate(gameState: DuckGameState, update: DuckUpdate) {
        val (board, players) = gameState
        val (player, point, newType) = update

        fun checkPlayer() = player == gameState.currentPlayer
        fun checkNewSquareType() = newType != Cell.FREE && newType != Cell.BLOCKED
        fun checkPoint() = point in board
        fun checkCurrentSquareType() = board[point] == Cell.FREE

        when {
            !checkPlayer() -> throw NotACurrentPlayerException(
                player,
                gameState.currentPlayer,
                players
            )
            !checkNewSquareType() -> throw IllegalSquareTypeException(newType)
            !checkPoint() -> throw PointIsOutOfGameBoardException(point)
            !checkCurrentSquareType() -> throw CellNotEmptyException(
                point,
                board[point]
            )
        }
    }
}