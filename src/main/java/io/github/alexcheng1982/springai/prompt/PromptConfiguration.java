package io.github.alexcheng1982.springai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PromptConfiguration {

  @Bean
  public CookingService cookingService(ChatClient.Builder builder) {
    return new CookingService(builder.build());
  }
}
