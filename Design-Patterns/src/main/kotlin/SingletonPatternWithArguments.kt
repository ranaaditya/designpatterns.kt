/**
 * Singleton-Design-Pattern with arguments
 *
 * @author ranaaditya
 *
 * need a SingletonClassHolder to manage the singleton class with arguments
 */
open class SingleClasstonHolder<out T : Any, in A>(creator: (A) -> T) {

    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                return@synchronized checkInstanceAgain
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                return@synchronized created
            }
        }
    }
}

class SingletonClass private constructor(argumentClass: ArgumentClass) {
    init {
        // do something with context
    }

    companion object : SingleClasstonHolder<SingletonClass, ArgumentClass>(::SingletonClass)
}


// argument for other classes
open class ArgumentClass {

    // do your needed stuff here

}