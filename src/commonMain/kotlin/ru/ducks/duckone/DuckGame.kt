package ru.ducks.duckone

import ru.ducks.duckone.api.IGame
import ru.ducks.duckone.board.Board
import ru.ducks.duckone.board.set
import ru.ducks.duckone.internal.DuckGameStatusCalculator
import ru.ducks.duckone.internal.DuckGameUpdateValidator

object DuckGame : IGame<DuckGameState, DuckUpdate> {
    override fun applyUpdate(gameState: DuckGameState, update: DuckUpdate): DuckGameState {
        if (!gameState.isActive) return gameState

        DuckGameUpdateValidator.validateUpdate(gameState, update)
        return performUpdate(gameState, update)
    }

    private fun performUpdate(gameState: DuckGameState, update: DuckUpdate): DuckGameState {
        val board = updateBoard(gameState, update)
        return gameState.copy(
            board = board,
            currentPlayerIndex = gameState.nextPlayerIndex,
            gameStatus = DuckGameStatusCalculator.calculateStatus(
                board,
                update
            )
        )
    }

    private fun updateBoard(gameState: DuckGameState, update: DuckUpdate): Board {
        val newRows = gameState.board.map { it.toMutableList() }
        newRows[update.point] = update.cell
        return Board(newRows.map { it.toList() })
    }
}