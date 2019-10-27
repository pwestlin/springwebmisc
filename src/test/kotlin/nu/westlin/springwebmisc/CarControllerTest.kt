package nu.westlin.springwebmisc

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import nu.westlin.springwebmisc.Car.Brand.VOLVO
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringJUnitConfig
@WebMvcTest(controllers = [CarController::class])
internal open class CarControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var repository: CarRepository

    @Test
    fun `all cars`() {
        every { repository.all() } returns cars

        mvc.get("/cars") {
            accept = APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(APPLICATION_JSON) }
            content { json(objectMapper.writeValueAsString(cars)) }
        }
    }

    @Test
    fun `find car by id`() {
        val car = cars.last()
        every { repository.findById(car.id) } returns car

        mvc.get("/cars/${car.id}") {
            accept = APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(APPLICATION_JSON) }
            content { json(objectMapper.writeValueAsString(car)) }
        }
    }

    @Test
    fun `find car by id that does not exist`() {
        val id = Integer.MAX_VALUE
        every { repository.findById(id) } returns null

        mvc.get("/cars/$id") {
            accept = APPLICATION_JSON
        }.andExpect {
            status { isNotFound }
            content { string("") }
        }
    }

    @Test
    fun `find cars by brand`() {
        val brand = VOLVO
        val cars = cars.filter { it.brand == brand }
        every { repository.findByBrand(brand) } returns cars

        mvc.get("/cars/brand/${brand}") {
            accept = APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(APPLICATION_JSON) }
            content { json(objectMapper.writeValueAsString(cars)) }
        }
    }

    @Test
    fun `find car by brand that does not exist`() {
        val brand = VOLVO
        every { repository.findByBrand(brand) } returns emptyList()

        mvc.get("/cars/brand/${brand}") {
            accept = APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(APPLICATION_JSON) }
            content { json("[]") }
        }
    }

    @Test
    fun `find cars by year`() {
        val cars = listOf(subaru2000, porsche2007)
        every { repository.findByYear(subaru2000.year, porsche2007.year) } returns cars

        mvc.get("/cars/year") {
            accept = APPLICATION_JSON
            param("startYear", subaru2000.year.toString())
            param("endYear", porsche2007.year.toString())
        }.andExpect {
            status { isOk }
            content { contentType(APPLICATION_JSON) }
            content { json(objectMapper.writeValueAsString(cars)) }
        }
    }

    @Test
    fun `find cars by year none matches`() {
        every { repository.findByYear(1, 2) } returns emptyList()

        mvc.get("/cars/year") {
            accept = APPLICATION_JSON
            param("startYear", 1.toString())
            param("endYear", 2.toString())
        }.andExpect {
            status { isOk }
            content { contentType(APPLICATION_JSON) }
            content { json("[]") }
        }
    }

}