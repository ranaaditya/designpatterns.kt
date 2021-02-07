package builder

/**
 * @author ranaaditya
 *
 *  The intent of the Builder design pattern is to separate the construction of a complex object from its representation.
 */
class FoodOrder private constructor(builder: FoodOrder.Builder) {

    val bread: String?
    val condiments: String?
    val meat: String?
    val fish: String?

    init {
        this.bread = builder.bread
        this.condiments = builder.condiments
        this.meat = builder.meat
        this.fish = builder.fish
    }

    /**
     * Builder class for the outer class,
     * adds a level of abstraction in object creation
     */
    class Builder {
        var bread: String? = null
            private set
        var condiments: String? = null
            private set
        var meat: String? = null
            private set
        var fish: String? = null
            private set

        fun bread(bread: String) = apply { this.bread = bread }

        fun condiments(condiments: String) = apply { this.condiments = condiments }

        fun meat(meat: String) = apply { this.meat = meat }

        fun fish(fish: String) = apply { this.fish = fish }

        fun build() = FoodOrder(this)

    }

}

// Abstracted and easier call to complex object creation
val foodOrder = FoodOrder.Builder()
                .bread("Naan")
                .condiments("Chilli sausace")
                .fish("sushi")
                .meat("freah")
                .build()
