package nu.westlin.springwebmisc

data class Car(val id: Int, val brand: Brand, val year: Int) {

    enum class Brand(private val value: String) {
        VOLVO("Volvo"),
        PORSCHE("Porsche"),
        SUBARU("Subaru");

        companion object {
            fun fromString(value: String): Brand {
                return values().find { it.value.toLowerCase() == value.toLowerCase() }
                    ?: throw IllegalArgumentException("'$value' is not a valid brand")
            }
        }
    }

}