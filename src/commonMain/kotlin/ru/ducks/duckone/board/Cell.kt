package ru.ducks.duckone.board

enum class Cell {
    FREE, BLOCKED, DOT, DUCK;

    override fun toString() = when (this) {
        BLOCKED -> ". "
        FREE -> "□ "
        DUCK -> "\uD83E\uDD86"
        DOT -> "■ ͏"
    }
}