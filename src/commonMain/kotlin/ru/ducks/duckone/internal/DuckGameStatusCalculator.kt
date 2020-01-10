package ru.ducks.duckone.internal

import ru.ducks.duckone.DuckGameStatus
import ru.ducks.duckone.DuckUpdate
import ru.ducks.duckone.board.Board
import ru.ducks.duckone.board.Cell
import ru.ducks.duckone.board.Row

internal object DuckGameStatusCalculator {
    private const val dotsInRowToWin = 4

    fun calculateStatus(board: Board, update: DuckUpdate): DuckGameStatus {
        return when {
            isWin(
                board,
                update
            ) -> DuckGameStatus.VICTORY(update.player)
            !board.anySquareLeft() -> DuckGameStatus.DRAW
            else -> DuckGameStatus.ACTIVE
        }
    }

    private fun Board.anySquareLeft() = flatten().any { it != Cell.FREE }

    private fun isWin(board: Board, update: DuckUpdate): Boolean {
        if (update.cell !== Cell.DOT)
            return false

        val (row0, column0) = update.point

        infix fun IntRange.coerceIn(range: IntRange) = maxOf(first, range.first)..minOf(last, range.last)

        val checkRadius = dotsInRowToWin - 1
        val neighbourRowsIndices = (row0 - checkRadius)..(row0 + checkRadius) coerceIn board.indices
        val neighbourColumnIndices = (column0 - checkRadius)..(column0 + checkRadius) coerceIn board.first().indices

        val rowNeighbours = neighbourRowsIndices.map { row -> board[row][column0] }
        val columnNeighbours = neighbourColumnIndices.map { column -> board[row0][column] }

        fun checkRow(row: Row) = row
            .windowed(dotsInRowToWin, partialWindows = false) { squares ->
                squares.all { it == Cell.DOT }
            }
            .any { it }

        return checkRow(rowNeighbours) || checkRow(columnNeighbours)
    }
}