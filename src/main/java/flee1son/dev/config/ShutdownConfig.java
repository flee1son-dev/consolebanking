package flee1son.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicBoolean;

@Configuration
public class ShutdownConfig {

    @Bean
    public AtomicBoolean running() {
        return new AtomicBoolean(true);
    }

}

