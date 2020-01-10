package ru.ducks.duckone.board

data class Point(val row: Int, val column: Int)

internal operator fun List<List<*>>.contains(point: Point): Boolean = point.row in indices && point.column in this[point.row].indices

internal operator fun <T> List<List<T>>.get(point: Point): T = this[point.row][point.column]

internal operator fun <T> List<MutableList<T>>.set(point: Point, value: T) {
    this[point.row][point.column] = value
}