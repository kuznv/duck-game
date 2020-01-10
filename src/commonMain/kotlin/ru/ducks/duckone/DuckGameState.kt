package ru.ducks.duckone

import ru.ducks.duckone.api.IGameState
import ru.ducks.duckone.api.IPlayer
import ru.ducks.duckone.board.Board

data class DuckGameState(
    val board: Board,
    val players: List<IPlayer>,
    val currentPlayerIndex: Int = 0,
    val gameStatus: DuckGameStatus = DuckGameStatus.ACTIVE
) : IGameState {

    init {
        require(players.isNotEmpty())
        require(currentPlayerIndex in players.indices)
    }

    override val isActive: Boolean get() = gameStatus == DuckGameStatus.ACTIVE
    val currentPlayer: IPlayer get() = players[currentPlayerIndex]
    val nextPlayerIndex get() = (currentPlayerIndex + 1) % players.size
    val nextPlayer get() = players[nextPlayerIndex]
}