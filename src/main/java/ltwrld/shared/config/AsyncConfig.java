package ltwrld.shared.config;

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "asyncTaskExecutor")
    ThreadPoolTaskExecutor asyncTaskExecutor(
            ThreadPoolTaskExecutorBuilder builder
    ) {
        return builder
                .corePoolSize(5)
                .maxPoolSize(10)
                .queueCapacity(100)
                .threadNamePrefix("async-")
                .build();
    }
}
