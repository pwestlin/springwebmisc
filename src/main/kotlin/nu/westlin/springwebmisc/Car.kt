package nu.westlin.springwebmisc

data class Car(val id: Int, val brand: Brand, val year: Int) {

    enum class Brand(private val value: String) {
        VOLVO("Volvo"),
        PORSCHE("Porsche"),
        SUBARU("Subaru");

        override fun toString(): String {
            return value
        }

        companion object {
            fun fromString(value: String): Brand {
                return values().find { it.value == value } ?: throw IllegalArgumentException("'$value' is not a valid brand")
            }
        }
    }

}