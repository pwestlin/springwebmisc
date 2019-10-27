package nu.westlin.springwebmisc

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import nu.westlin.springwebmisc.Car.Brand.VOLVO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpStatus
import org.springframework.web.client.getForEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
internal class CarControllerIntegrationTest {

    private val restTemplate = RestTemplateBuilder().rootUri("http://localhost:8080/cars").build()

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `all cars`() {
        restTemplate.getForEntity<String>("/").let {
            assertThat(it.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(objectMapper.readValue<List<Car>>(it.body!!)).containsExactlyElementsOf(cars)
        }
    }

    @Test
    fun `find car by id`() {
        val car = porsche2007
        restTemplate.getForEntity<String>("/${car.id}").let {
            assertThat(it.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(objectMapper.readValue<Car>(it.body!!)).isEqualTo(car)
        }
    }

    @Test
    fun `find cars by brand`() {
        val brand = VOLVO
        restTemplate.getForEntity<String>("/brand/${brand}").let {
            assertThat(it.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(objectMapper.readValue<List<Car>>(it.body!!)).containsExactlyInAnyOrder(volvo1964, volvo2019)
        }
    }
}