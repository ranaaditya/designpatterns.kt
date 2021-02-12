package observer

import kotlin.properties.Delegates

/**
 * @author ranaaditya
 *
 * Observer Design pattern
 */
interface ChangeListener {
    fun onChangeInitiated(oldValue: Any, changedValue: Any)
}

class ShowChangeListener: ChangeListener {
    override fun onChangeInitiated(oldValue: Any, changedValue: Any) {
        println("Value has been changed to ${changedValue.toString()} from ${oldValue.toString()}")
    }
}

class ObservableObject(changeListener: ChangeListener) {
    var text: String by Delegates.observable(
            initialValue = "",
            onChange = {
                property, oldValue, newValue ->
                changeListener.onChangeInitiated(oldValue, newValue)
            }
    )
}

fun main() {
    val observableObject = ObservableObject(ShowChangeListener())
    observableObject.text = "hello"
    observableObject.text = "hallo"
}