package nu.westlin.springwebmisc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.router

@Configuration
class RoutesConfiguration {

    @Bean
    fun routes(carHandler: CarHandler) = router {
        // TODO petves: Filter

        accept(MediaType.APPLICATION_JSON).nest {
            "/cars".nest {
                GET("/", carHandler::all)
                GET("/year", carHandler::findByYear)
                GET("/{id}", carHandler::findById)
                GET("/brand/{brand}", carHandler::findByBrand)
            }
        }
    }
}

