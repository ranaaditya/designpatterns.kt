package strategy

/**
 * @author ranaaditya
 *
 * Strategy Design Pattern
 */
interface OrderCommand {
    fun execute()
}

class AddNewOrderCommand(val itemId: Int) : OrderCommand {
    override fun execute() {
        println("Add new order, containing ID : $itemId")
    }
}

class PayOrderCommand(val itemId: Int) : OrderCommand {
    override fun execute() {
        println("Pay for your itemID: $itemId")
    }
}

class OrderCommandProcessor {
    private val orderQueue = ArrayList<OrderCommand>()

    fun addNewOrder(orderCommand: OrderCommand): OrderCommandProcessor = apply {
        orderQueue.add(orderCommand)
    }

    fun processBill(): OrderCommandProcessor = apply {
        println("Processing order ...")
        orderQueue.forEach { it.execute() }
        orderQueue.clear()
    }
}

fun main() {
    OrderCommandProcessor()
            .addNewOrder(AddNewOrderCommand(1))
            .addNewOrder(AddNewOrderCommand(2))
            .addNewOrder(AddNewOrderCommand(3))
            .addNewOrder(AddNewOrderCommand(4))
            .processBill()
}