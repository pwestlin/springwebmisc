package nu.westlin.springwebmisc

import nu.westlin.springwebmisc.Car.Brand.SUBARU
import nu.westlin.springwebmisc.Car.Brand.VOLVO
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class CarTest {

    @Test
    fun `check toString`() {
        assertThat(VOLVO.toString()).isEqualTo("Volvo")
    }

    @Test
    fun `create Brand from string`() {
        assertThat(Car.Brand.fromString("Subaru")).isEqualTo(SUBARU)
    }

    @Test
    fun `create Brand from string should throw IllegalArgumentException`() {
        assertThatThrownBy {Car.Brand.fromString("Does not exist") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("'Does not exist' is not a valid brand")
    }
}