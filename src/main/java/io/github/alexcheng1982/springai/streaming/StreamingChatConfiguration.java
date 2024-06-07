package io.github.alexcheng1982.springai.streaming;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StreamingChatConfiguration {

  @Bean
  public StreamingChatService streamingChatService(
      ChatClient.Builder builder) {
    return new StreamingChatService(builder.build());
  }
}
