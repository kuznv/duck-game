import ru.ducks.duckone.*
import ru.ducks.duckone.board.Board
import ru.ducks.duckone.board.Cell
import ru.ducks.duckone.board.Point
import kotlin.test.Test
import kotlin.test.assertEquals

class DuckGameStatusCalculatorTest {
    @Test
    fun `parsing board`() {
        assertEquals(
            Board.create { row, column ->
                when {
                    row == 0 || column == 0 || row == 5 || column == 5 -> Cell.BLOCKED
                    row == 1 && column == 3 -> Cell.DOT
                    row == 3 && column == 2 -> Cell.DUCK
                    else -> Cell.FREE
                }
            },
            readBoard("parsing board")
        )
    }


    @Test
    fun `placing squares`() {
        val placeDot = DuckGame.applyUpdate(
            defaultGameState(borderedBoard),
            DuckUpdate(
                player,
                Point(1, 3),
                Cell.DOT
            )
        )
        val placeDuck = DuckGame.applyUpdate(
            placeDot,
            DuckUpdate(
                nextPlayer,
                Point(4, 1),
                Cell.DUCK
            )
        )

        assertEquals(
            readBoard("placing squares"),
            placeDuck.board
        )
    }

    @Test
    fun `4 vertical dots should win`() {
        assertEquals(
            DuckGameStatus.VICTORY(player),
            DuckGame.applyUpdate(
                defaultGameState("4 vertical dots should win"),
                DuckUpdate(
                    player,
                    Point(2, 2),
                    Cell.DOT
                )
            ).gameStatus
        )
    }

    @Test
    fun `4 horizontal dots should win`() {
        assertEquals(
            DuckGameStatus.VICTORY(player),
            DuckGame.applyUpdate(
                defaultGameState("4 horizontal dots should win"),
                DuckUpdate(
                    player,
                    Point(1, 2),
                    Cell.DOT
                )
            ).gameStatus
        )
    }

    @Test
    fun `no dot no win`() {
        assertEquals(
            DuckGameStatus.ACTIVE,
            DuckGame.applyUpdate(
                defaultGameState("no dot no win"),
                DuckUpdate(
                    player,
                    Point(1, 2),
                    Cell.DUCK
                )
            ).gameStatus
        )
    }

    @Test
    fun `4 dots not in row should not win`() {
        assertEquals(
            DuckGameStatus.ACTIVE,
            DuckGame.applyUpdate(
                defaultGameState("4 dots not in row should not win"),
                DuckUpdate(
                    player,
                    Point(5, 2),
                    Cell.DOT
                )
            ).gameStatus
        )
    }
}