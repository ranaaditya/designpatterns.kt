package chainofresposibility

/**
 * @author ranaaditya
 *
 * Chain of Responsibility Design Pattern
 *
 * Chain of responsibility pattern is used to achieve loose coupling
 * in software design where a request from the client is passed to a
 * chain of objects to process them. Later, the object in the chain
 * will decide themselves who will be processing the request and whether
 * the request is required to be sent to the next object in the chain or not.
 */
class Chain {
    var chain: Processor? = null
    private fun buildChain() {
        chain = NegativeProcessor(ZeroProcessor(PositiveProcessor(null)))
    }

    fun process(request: processNumber?) {
        chain!!.process(request)
    }

    init {
        buildChain()
    }
}

abstract class Processor(private val processor: Processor?) {
    open fun process(request: processNumber?) {
        processor?.process(request)
    }
}

class processNumber(val number: Int)

class NegativeProcessor(processor: Processor) : Processor(processor) {
    override fun process(request: processNumber?) {
        if (request!!.number < 0) {
            println("NegativeProcessor : " + request.number)
        } else {
            super.process(request)
        }
    }
}

class ZeroProcessor(processor: Processor) : Processor(processor) {
    override fun process(request: processNumber?) {
        if (request!!.number == 0) {
            println("ZeroProcessor : " + request.number)
        } else {
            super.process(request)
        }
    }
}

class PositiveProcessor(processor: Processor?) : Processor(processor) {
    override fun process(request: processNumber?) {
        if (request!!.number > 0) {
            println("PositiveProcessor : " + request.number)
        } else {
            super.process(request)
        }
    }
}

fun main() {
    val chain = Chain()
    chain.process(processNumber(9))
    chain.process(processNumber(1))
    chain.process(processNumber(1))
}