package nu.westlin.springwebmisc

import nu.westlin.springwebmisc.Car.Brand
import org.springframework.stereotype.Repository

@Repository
class CarRepository {
    private val cars = ArrayList<Car>()

    /**
     * Initialises repo with some cars
     */
    fun init() {
        cars.addAll(nu.westlin.springwebmisc.cars)
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