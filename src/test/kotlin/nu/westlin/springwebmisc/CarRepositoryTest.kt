package nu.westlin.springwebmisc

import nu.westlin.springwebmisc.Car.Brand.VOLVO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CarRepositoryTest {

    private val repository: CarRepository = CarRepository().apply { init() }

    @Test
    fun `create`() {
        assertThat(CarRepository().all()).isEmpty()
    }

    @Test
    fun `should have been initialised with cars`() {
        assertThat(repository.all())
            .containsExactly(volvo1964, volvo2019, porsche2007, subaru2000)
    }

    @Test
    fun `find by id`() {
        assertThat(repository.findById(volvo1964.id)).isEqualTo(volvo1964)
    }

    @Test
    fun `find by id - not found`() {
        assertThat(repository.findById(Integer.MAX_VALUE)).isNull()
    }

    @Test
    fun `find by brand`() {
        assertThat(repository.findByBrand(VOLVO)).containsExactlyInAnyOrder(volvo1964, volvo2019)
    }

    @Test
    fun `find by year`() {
        assertThat(repository.findByYear(2000, 2007)).containsExactly(subaru2000, porsche2007)
    }

    @Test
    fun `find by year - none found`() {
        assertThat(repository.findByYear(1, 2)).isEmpty()
    }
}