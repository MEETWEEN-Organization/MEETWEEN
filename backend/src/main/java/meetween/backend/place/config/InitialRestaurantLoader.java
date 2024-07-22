package meetween.backend.place.config;

import meetween.backend.place.application.ScrappingService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "enable.initialize_restaurant", havingValue = "true")
public class InitialRestaurantLoader implements ApplicationRunner {

    private final ScrappingService scrappingService;

    public InitialRestaurantLoader(ScrappingService scrappingService) {
        this.scrappingService = scrappingService;
    }

    @Override
    public void run(ApplicationArguments args) {
        scrappingService.scrap();
    }
}