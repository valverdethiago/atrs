package com.crossover.atrs.test.service;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.crossover.atrs.respository.DatabaseConfiguration;
import com.crossover.atrs.service.ServiceConfiguration;

@Configuration
@Import({ServiceConfiguration.class, DatabaseConfiguration.class})
public class TestConfiguration {
    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(ArrayUtils.addAll(
                        new PathMatchingResourcePatternResolver().getResources("classpath*:atrs.properties")
                )
        );

        return ppc;
    }
}
