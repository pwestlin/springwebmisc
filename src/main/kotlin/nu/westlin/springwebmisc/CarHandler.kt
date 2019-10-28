package nu.westlin.springwebmisc

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

// TODO petves: Not annotation
@Component
class CarHandler(private val repository: CarRepository) {

    fun all(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().body(repository.all())
    }

    fun findById(request: ServerRequest): ServerResponse {
        val car = repository.findById(request.pathVariable("id").toInt())
        return car?.let { ServerResponse.ok().body(car) } ?: ServerResponse.notFound().build()
    }

    fun findByBrand(request: ServerRequest): ServerResponse {
        // TODO petves: What to do if brand is null or can't be converted to a Brand?
        return ServerResponse.ok().body(repository.findByBrand(Car.Brand.fromString(request.pathVariable("brand"))))
    }

    fun findByYear(request: ServerRequest): ServerResponse {
        // TODO petves: toInt can throw an exception...
        val startYear = request.param("startYear").orElse(null).toInt()
        // TODO petves: toInt can throw an exception...
        val endYear = request.param("endYear").orElse(null).toInt()
        return if (startYear != null && endYear != null) {
            ServerResponse.ok().body(repository.findByYear(startYear, endYear))
        } else {
            ServerResponse.badRequest().contentType(MediaType.TEXT_PLAIN).body("Both query params startYear and endYear are required")
        }
    }
}