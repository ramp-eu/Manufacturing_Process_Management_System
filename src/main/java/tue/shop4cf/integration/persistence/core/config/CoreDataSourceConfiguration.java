package tue.shop4cf.integration.persistence.core.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        transactionManagerRef = "camundaTransactionManager")
public class CoreDataSourceConfiguration {
    @Bean(name="camundaProperties")
    @ConfigurationProperties("core.datasource")
    public DataSourceProperties camundaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name="camundaBpmDataSource")
    @ConfigurationProperties(prefix="core.datasource")
    public DataSource camundaBpmDataSource(@Qualifier("camundaProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Autowired
    @Bean(name="camundaTransactionManager")
    public PlatformTransactionManager camundaTransactionManager(@Qualifier("camundaBpmDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
