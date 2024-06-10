package io.github.alexcheng1982.springai.outputconverter;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class OutputConverterConfiguration {

  @Bean
  public OutputConverterChatService outputConverterChatService(
      ChatClient.Builder builder, ListOutputConverter listOutputConverter,
      MapOutputConverter mapOutputConverter) {
    return new OutputConverterChatService(builder.build(), listOutputConverter,
        mapOutputConverter);
  }

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
