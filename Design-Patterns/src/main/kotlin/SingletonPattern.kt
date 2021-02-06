/**
 * for simple purpose use inbuilt object
 *
 * example:
 *
 * object singletonClass {
 *
 *  // do your stuff
 *
 *  }
 *
 */

/**
 * @author ranaaditya
 *
 * Singleton Design-Pattern
 *
 * @constructor is private
 *
 * Rules for making a class singleton:
 *
 * 1. A private constructor
 *
 * 2. A static reference of its class
 *
 * 3. One static method
 *
 * 4. Globally accessible object reference
 *
 * 5. Consistency across multiple threads
 *
 *
 */
public open class SingletonPattern private constructor() {

    init {
        numberOfInstances++
    }

    companion object {
        var numberOfInstances: Int = 0
        var instance: SingletonPattern? = null

        fun getSingletonInstance(): SingletonPattern? {

            if (instance == null) {

                // synchronizing the state to ensure that there is no thread interference
                synchronized(SingletonPattern::class) {
                    if (instance == null) {
                        instance = SingletonPattern()
                    }
                }
            }

            return instance
        }
    }

    fun printNumberOfInstances() = println("Total number of instances of ${this.toString()} class are: ${numberOfInstances.toString()}")

    fun printMessage() = println("Hello from the Singleton instance")
}
