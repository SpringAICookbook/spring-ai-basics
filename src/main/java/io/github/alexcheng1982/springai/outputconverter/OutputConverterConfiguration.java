package io.github.alexcheng1982.springai.outputconverter;

import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class OutputConverterConfiguration {

  @Bean
  public ListOutputConverter listOutputConverter(
      DefaultConversionService conversionService) {
    return new ListOutputConverter(conversionService);
  }

  @Bean
  public MapOutputConverter mapOutputConverter() {
    return new MapOutputConverter();
  }
}
