package io.github.alexcheng1982.springai.streaming;

import org.springframework.ai.chat.StreamingChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StreamingChatConfiguration {

  @Bean
  public StreamingChatService streamingChatService(
      StreamingChatClient chatClient) {
    return new StreamingChatService(chatClient);
  }
}
