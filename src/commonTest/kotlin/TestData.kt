import ru.ducks.duckone.api.IPlayer
import ru.ducks.duckone.DuckGameState
import ru.ducks.duckone.board.Board
import ru.ducks.duckone.board.Cell

expect fun readResource(file: String): String

data class DuckPlayer(val name: String) : IPlayer

val nikita = DuckPlayer("nikita")
val tortik = DuckPlayer("tortik")
val players = listOf(nikita, tortik)

fun readBoard(name: String): Board {
    val board = readResource("DuckGameTest/$name.tsv")
    return parseBoard(board)
}

fun parseBoard(string: String): Board {
    val lines = string.lines().map { it.split('\t') }
    return Board.create { row, column ->
        when (val c = lines[row][column].firstOrNull()) {
            '*' -> Cell.BLOCKED
            ' ', '(' -> Cell.FREE
            '.' -> Cell.DOT
            'd'-> Cell.DUCK
            else -> error("'$c'")
        }
    }
}

val emptyBoard = readBoard("empty")
val borderedBoard = readBoard("bordered")

fun defaultPlayer() = object : IPlayer {}

val player = players[0]
val nextPlayer = players[1]

fun defaultGameState(board: String) = defaultGameState(readBoard(board))
fun defaultGameState(board: Board) =
    DuckGameState(
        board = board,
        players = players
    )