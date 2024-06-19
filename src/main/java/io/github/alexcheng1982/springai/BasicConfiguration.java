package io.github.alexcheng1982.springai;

import io.github.alexcheng1982.springai.observation.LoggingAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicConfiguration {

  @Bean
  public LoggingAdvisor loggingAdvisor() {
    return new LoggingAdvisor();
  }
}
