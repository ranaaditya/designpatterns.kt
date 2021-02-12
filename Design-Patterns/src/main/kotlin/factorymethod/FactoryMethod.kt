package factorymethod

/**
 * @author ranaaditya
 */
interface Book {
    fun getInfo()
    fun order()
    fun rate()
}

enum class Genre {
    SCIENCE, LITERATURE
}

/**
 * Factory Method defines an interface for creating objects,
 * but lets subclasses decide which classes to instantiate
 *
 * these methods are usually called within Template Method
 *
 * A Factory Method enforces that encapsulation, and allows an object
 * to be requested without inextricable coupling to the act of creation.
 */
class BookFactory {
    companion object {
        fun createBook(genre: Genre): Book = when (genre) {
            Genre.SCIENCE -> object : Book {
                private val title = "Science book !"
                override fun getInfo() = println(title)
                override fun order() = println("Order $title")
                override fun rate() = println("Rate for $title")
            }

            Genre.LITERATURE -> object : Book {
                private val title = "Literature book !"
                override fun getInfo() = println(title)
                override fun order() = println("Order $title")
                override fun rate() = println("Rate for $title")
            }
        }
    }
}