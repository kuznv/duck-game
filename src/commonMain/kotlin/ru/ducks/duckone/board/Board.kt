package ru.ducks.duckone.board

typealias Row = List<Cell>

class Board(private val rows: List<Row>) : List<Row> by rows {

    override fun equals(other: Any?) = other is Board && other.rows == rows

    override fun hashCode() = rows.hashCode()

    override fun toString() =
        joinToString(prefix = "\n", separator = "\n") { row ->
            row.joinToString(separator = "")
        }

    companion object {
        fun create(
            rows: Int = 6,
            columns: Int = 6,
            init: (row: Int, column: Int) -> Cell = { _, _ -> Cell.FREE }
        ) = Board(
            List(rows) { row ->
                List(columns) { column ->
                    init(row, column)
                }
            }
        )
    }
}