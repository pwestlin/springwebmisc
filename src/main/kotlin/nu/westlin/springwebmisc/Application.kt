package nu.westlin.springwebmisc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@Component
class MyApplicationListener(private val carRepository: CarRepository) : ApplicationListener<ApplicationReadyEvent> {
    // TODO petves: Should this be on the repo itself?
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        carRepository.init()
    }
}
