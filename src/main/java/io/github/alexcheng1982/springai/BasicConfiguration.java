package io.github.alexcheng1982.springai;

import io.github.alexcheng1982.springai.observation.LoggingChatClient;
import org.springframework.ai.chat.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BasicConfiguration {

  @Primary
  @Bean
  public ChatClient chatClient(ChatClient chatClient) {
    return new LoggingChatClient(chatClient);
  }
}
