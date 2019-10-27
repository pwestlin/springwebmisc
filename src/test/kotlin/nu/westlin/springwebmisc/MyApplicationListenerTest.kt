package nu.westlin.springwebmisc

import io.mockk.called
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.boot.context.event.ApplicationReadyEvent

internal class MyApplicationListenerTest {

    private val carRepository = mockk<CarRepository>(relaxed = true)
    private val event = mockk<ApplicationReadyEvent>()
    private val listener = MyApplicationListener(carRepository)

    @Test
    fun `application started event should initialize carRepository`() {
        listener.onApplicationEvent(event)

        verify {
            carRepository.init()
            event wasNot called
        }
    }
}