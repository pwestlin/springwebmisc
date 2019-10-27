package nu.westlin.springwebmisc

import nu.westlin.springwebmisc.Car.Brand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/cars")
class CarController(private val repository: CarRepository) {

    @GetMapping("")
    fun all(): List<Car> {
        return repository.all()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int, response: HttpServletResponse): Car? {
        val car = repository.findById(id)
        if (car == null) {
            response.status = HttpStatus.NOT_FOUND.value()
        }
        return car
    }

    @GetMapping("/brand/{brand}")
    fun findByBrand(@PathVariable brand: Brand): List<Car> {
        return repository.findByBrand(brand)
    }

    @GetMapping("/year")
    fun findByYear(@RequestParam startYear: Int, @RequestParam endYear: Int): List<Car> {
        return repository.findByYear(startYear, endYear)
    }
}