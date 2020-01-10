package ru.ducks.duckone

import ru.ducks.duckone.api.GameRunner
import ru.ducks.duckone.api.IUpdateSource

open class DuckGameRunner(
    initialState: DuckGameState,
    updateSource: IUpdateSource<DuckUpdate>
) : GameRunner<DuckGameState, DuckUpdate>(DuckGame, initialState, updateSource)