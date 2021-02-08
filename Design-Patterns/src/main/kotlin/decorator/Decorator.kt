package decorator

/**
 * @author ranaaditya
 *
 * Decorator Design Pattern allows to dynamically add functionality
 * and behaviour to an object without affecting the behaviour of other
 * objects in the same class
 */
interface Shape {
    fun draw()
    fun resize()
    fun description(): String
}

public open class Circle : Shape {
    override fun draw() {
        println("Drawing Circle")
    }

    override fun resize() {
        println("Resizing the Circle")
    }

    override fun description(): String {
        return "This is a Circle"
    }
}

public open class Rectangle : Shape {
    override fun draw() {
        println("Drawing Rectangle")
    }

    override fun resize() {
        println("Resizing the Rectangle")
    }

    override fun description(): String {
        return "This is a Rectangle"
    }
}

public abstract class ShapeDecorator(decorator: Shape) : Shape {
    protected lateinit var decoratorShape: Shape

    init {
        this.decoratorShape = decorator
    }
}

public enum class Color {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    WHITE,
    BLACK,
    ORANGE,
    MAROON
}

public enum class LineStyle {
    SOLID,
    DASH,
    DOT
}

public open class FillColorDecorator(decoratedShape: Shape, color: Color) : ShapeDecorator(decoratedShape) {
    private lateinit var color: Color

    init {
        this.color = color
    }

    override fun draw() {
        decoratorShape.draw()
    }

    override fun resize() {
        decoratorShape.resize()
    }

    override fun description(): String {
        return decoratorShape.description() + "filled with $color color"
    }
}

public open class LineColorDecorator(decoratorShape: Shape, color: Color) : ShapeDecorator(decoratorShape) {
    private lateinit var color: Color

    init {
        this.color = color
    }

    override fun draw() {
        decoratorShape.draw()
    }

    override fun resize() {
        decoratorShape.resize()
    }

    override fun description(): String {
        return decoratorShape.description() + "drawn with $color line color"
    }
}

fun main() {

    println("Creating simple shapes : ")
    val rectangle: Shape = Rectangle()
    val circle: Shape = Circle()

    rectangle.draw()
    circle.draw()

    println("Creating decorated shapes : ")

    val decoratedCircle: Shape = FillColorDecorator(LineColorDecorator(Circle(), Color.RED), Color.RED)
    decoratedCircle.draw()

}