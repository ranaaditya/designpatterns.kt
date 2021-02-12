package mediator

/**
 * @author ranaaditya
 *
 * Mediator Design Pattern
 *
 * This Design-Pattern define an object that encapsulates
 * how a set of objects interact. Mediator promotes loose
 * coupling by keeping objects from referring to each other
 * explicitly, and it lets you vary their interaction independently.
 */
abstract class Colleague(mediator: Mediator) {
    private var mediator: Mediator = mediator

    fun send(message: String) {
        mediator.sendMessage(message, this)
    }

    fun getMediator() = mediator

    abstract fun receive(message: String)

}

interface Mediator {
    fun sendMessage(message: String, colleague: Colleague)
}

class ApplicationMediator : Mediator {
    private var colleagues = ArrayList<Colleague>()

    override fun sendMessage(message: String, originator: Colleague) {
        for (col in colleagues) {
            if (col != originator) {
                col.receive(message)
            }
        }
    }

    fun addColleague(colleague: Colleague) {
        colleagues.add(colleague)
    }
}

class ConcreteColleague(mediator: Mediator) : Colleague(mediator) {
    override fun receive(message: String) {
        println("Colleague received: $message")
    }
}

class MobileColleague(mediator: Mediator) : Colleague(mediator) {
    override fun receive(message: String) {
        println("Mobile colleague received: $message")
    }
}

fun main() {
    val applicationMediator = ApplicationMediator()
    val concreteColleague = ConcreteColleague(applicationMediator)
    val mobileColleague = MobileColleague(applicationMediator)

    applicationMediator.addColleague(concreteColleague)
    applicationMediator.addColleague(mobileColleague)

    concreteColleague.send("hello world from mobile colleague")
    mobileColleague.send("hello world from mobile colleague")
}

