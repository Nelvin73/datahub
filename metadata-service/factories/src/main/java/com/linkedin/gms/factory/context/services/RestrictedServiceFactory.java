package com.linkedin.gms.factory.context.services;

import com.linkedin.metadata.spring.YamlPropertySourceFactory;
import io.datahubproject.metadata.services.RestrictedService;
import io.datahubproject.metadata.services.SecretService;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource(value = "classpath:/application.yml", factory = YamlPropertySourceFactory.class)
public class RestrictedServiceFactory {

  @Autowired
  @Qualifier("dataHubSecretService")
  private SecretService secretService;

  @Bean(name = "restrictedService")
  @Scope("singleton")
  @Nonnull
  protected RestrictedService getInstance() throws Exception {
    return new RestrictedService(secretService);
  }
}
