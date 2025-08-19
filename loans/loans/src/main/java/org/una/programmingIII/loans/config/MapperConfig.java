package org.una.programmingIII.loans.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration()
          .setFieldMatchingEnabled(true)
          .setFieldAccessLevel(AccessLevel.PRIVATE)
          .setMatchingStrategy(MatchingStrategies.STRICT);
        return mm;
    }
}
