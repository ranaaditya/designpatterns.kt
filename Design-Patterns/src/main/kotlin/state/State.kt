package state

import javafx.scene.paint.Stop

/**
 * @author ranaaditya
 *
 * State Design pattern
 *
 * Allow an object to alter its behaviour when its internal state changes.
 * The object will appear to change its class
 */
class Context() {
    private var gameState: State? = null

//    constructor(state: State?) {
//        gameState = state
//    }

    fun getState() = gameState
    fun setState(state: State) { gameState = state }
}

interface State {
    fun maintainState(context: Context)
}

class StartState: State {
    override fun maintainState(context: Context) {
        println("Game is in Start state")
        context.setState(this)
    }
}

class StopState: State {
    override fun maintainState(context: Context) {
        println("Game is in Stop state")
        context.setState(this)
    }
}

class RunningState: State {
    override fun maintainState(context: Context) {
        println("Game is in Running State")
        context.setState(this)
    }
}

fun main() {
    val context = Context()
    val startState = StartState()
    startState.maintainState(context)

    val runningState = RunningState()
    runningState.maintainState(context)

    val stopState = StopState()
    stopState.maintainState(context)

    println("Game has state ${context.getState()}")

}