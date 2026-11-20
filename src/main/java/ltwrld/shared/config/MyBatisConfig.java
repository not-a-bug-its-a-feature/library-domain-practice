package ltwrld.shared.config;

import ltwrld.shared.adapter.persistence.mybatis.UUIDTypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@MapperScan({
        "ltwrld.member.adapter.out.persistence"
})
public class MyBatisConfig {
    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration ->
                configuration.getTypeHandlerRegistry()
                        .register(UUID.class, UUIDTypeHandler.class);
    }
}
