package visitor

/**
 * @author ranaaditya
 *
 * Visitor Design Pattern
 *
 * It is used when we have to perform an operation on a group
 * of similar kind of Objects. With the help of visitor pattern,
 * we can move the operational logic from the objects to another class.
 *
 * It has two parts:
 *
 * 1. visit() -  implemented by the visitor and is called for every element
 *
 * 2. accept() - accepts a visitor
 */
interface ItemElement {
    fun accept(visitor: ShoppingCartVisitor?): Int
}

class Book(val price: Int, val isbnNumber: String) : ItemElement {

    override fun accept(visitor: ShoppingCartVisitor?): Int {
        return visitor!!.visit(this)
    }

}

class Fruit(val pricePerKg: Int, val weight: Int, val name: String) : ItemElement {

    override fun accept(visitor: ShoppingCartVisitor?): Int {
        return visitor!!.visit(this)
    }

}

interface ShoppingCartVisitor {
    fun visit(book: Book?): Int
    fun visit(fruit: Fruit?): Int
}

class ShoppingCartVisitorImpl : ShoppingCartVisitor {
    override fun visit(book: Book?): Int {
        var cost = 0
        //apply 5$ discount if book price is greater than 50
        cost = if (book!!.price > 50) {
            book!!.price - 5
        } else book.price
        println("Book ISBN::" + book!!.isbnNumber + " cost =" + cost)
        return cost
    }

    override fun visit(fruit: Fruit?): Int {
        val cost = fruit!!.pricePerKg * fruit!!.weight
        println(fruit!!.name + " cost = " + cost)
        return cost
    }
}

private fun calculatePrice(items: Array<ItemElement>): Int {
    val visitor: ShoppingCartVisitor = ShoppingCartVisitorImpl()
    var sum = 0
    for (item in items) {
        sum += item.accept(visitor)
    }
    return sum
}

fun main(args: Array<String>) {
    val items = arrayOf(Book(20, "1234"),
            Book(100, "5678"), Fruit(10, 2, "Banana"),
            Fruit(5, 5, "Apple"))
    val total = calculatePrice(items)
    println("Total Cost = $total")
}
