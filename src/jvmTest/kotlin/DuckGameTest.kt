import kotlinx.coroutines.runBlocking
import org.junit.Test
import ru.ducks.duckone.*
import ru.ducks.duckone.board.Cell
import ru.ducks.duckone.board.Point
import kotlin.test.assertEquals

class DuckGameTest {
    @Test
    fun `game runner test`() = runBlocking {
        val initialState = defaultGameState("bordered")

        val updateSource = object : DuckUpdateSource {
            private val updates = listOf(
                DuckUpdate(nikita, Point(4, 1), Cell.DOT),
                DuckUpdate(tortik, Point(4, 3), Cell.DOT),
                DuckUpdate(nikita, Point(4, 4), Cell.DOT),
                DuckUpdate(tortik, Point(4, 2), Cell.DUCK),
                DuckUpdate(nikita, Point(3, 2), Cell.DOT),
                DuckUpdate(tortik, Point(2, 2), Cell.DOT),
                DuckUpdate(nikita, Point(2, 1), Cell.DOT),
                DuckUpdate(tortik, Point(2, 3), Cell.DOT),
                DuckUpdate(nikita, Point(2, 4), Cell.DOT)
            )

            private val updateIterator = updates.iterator()

            override suspend fun getUpdate() = updateIterator.next()
        }

        val duckGameRunner = DuckGameRunner(initialState, updateSource)
        duckGameRunner.run()
        assertEquals(
            DuckGameState(
                readBoard("runner test"),
                players,
                currentPlayerIndex = players.indexOf(tortik),
                gameStatus = DuckGameStatus.VICTORY(nikita)
            ),
            duckGameRunner.state
        )
    }
}