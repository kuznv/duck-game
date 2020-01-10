package ru.ducks.duckone.api

open class GameRunner<S : IGameState, U : IUpdate>(
    private val game: IGame<S, U>,
    initialState: S,
    private val updateSource: IUpdateSource<U>
) : IGameSession<S> {

    final override var state: S = initialState
        protected set

    suspend fun run() {
        while (true) {
            val currentState = state
            if (!currentState.isActive) return

            val update = updateSource.getUpdate()
            val newState = game.applyUpdate(currentState, update)
            state = newState
            onGameUpdate(state)
        }
    }

    open fun onGameUpdate(state: S) {}
}