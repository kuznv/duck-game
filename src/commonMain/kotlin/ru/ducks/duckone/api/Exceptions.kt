package ru.ducks.duckone.api

import ru.ducks.duckone.board.Point
import ru.ducks.duckone.board.Cell

class CellNotEmptyException(point: Point, cell: Cell) : Throwable(
    message = "Cell at $point is occupied by $cell"
)

class PointIsOutOfGameBoardException(point: Point) : Throwable(
    message = "Point $point is out of game board"
)

class IllegalSquareTypeException(cell: Cell) : Throwable(
    message = "Can't place squares of $cell"
)

class NotACurrentPlayerException(player: IPlayer, currentPlayer: IPlayer, players: Iterable<IPlayer>) : Throwable(
    message = "Player $player is not a current player $currentPlayer, all players: ${players.joinToString()}"
)

class GameFinishedException(gameState: IGameState) : Throwable(
    message = "Current game status: $gameState"
)