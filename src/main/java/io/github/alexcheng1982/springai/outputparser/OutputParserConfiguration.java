package io.github.alexcheng1982.springai.outputparser;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.parser.ListOutputParser;
import org.springframework.ai.parser.MapOutputParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class OutputParserConfiguration {

  @Bean
  public DefaultConversionService defaultConversionService() {
    return new DefaultConversionService();
  }

  @Bean
  public ListOutputParser listOutputParser(
      DefaultConversionService conversionService) {
    return new ListOutputParser(conversionService);
  }

  @Bean
  public MapOutputParser mapOutputParser() {
    return new MapOutputParser();
  }

  @Bean
  public OutputParserChatService chatService(
      ChatClient.Builder builder,
      ListOutputParser listOutputParser,
      MapOutputParser mapOutputParser) {
    return new OutputParserChatService(builder.build(), listOutputParser,
        mapOutputParser);
  }
}
