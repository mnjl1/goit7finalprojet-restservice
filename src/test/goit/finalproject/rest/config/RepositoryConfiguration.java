package goit.finalproject.rest.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"goit.finalproject.rest.model"})
@EnableJpaRepositories(basePackages = {"goit.finalproject.rest.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
