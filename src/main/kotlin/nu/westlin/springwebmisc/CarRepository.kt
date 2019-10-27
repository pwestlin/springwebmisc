package nu.westlin.springwebmisc

import nu.westlin.springwebmisc.Car.Brand
import nu.westlin.springwebmisc.Car.Brand.PORSCHE
import nu.westlin.springwebmisc.Car.Brand.SUBARU
import nu.westlin.springwebmisc.Car.Brand.VOLVO
import org.springframework.stereotype.Repository

@Repository
class CarRepository {
    private val cars = ArrayList<Car>()

    /**
     * Initialises repo with some cars
     */
    fun init() {
        cars.addAll(listOf(Car(1, VOLVO, 1964), Car(2, VOLVO, 2019), Car(3, PORSCHE, 2007), Car(4, SUBARU, 2000)))
    }

    /**
     * Returns all cars
     */
    fun all() = cars.toList()

    /**
     * Returns car with [id] or null if none is found.
     */
    fun findById(id: Int) = cars.firstOrNull { it.id == id }

    /**
     * Returns all cars with [brand].
     */
    fun findByBrand(brand: Brand) = cars.filter { it.brand == brand }

    /**
     * Returns all cars with year between [startYear] and [endYear].
     */
    fun findByYear(startYear: Int, endYear: Int) = cars.filter { it.year in startYear..endYear }.sortedBy { it.year }
}