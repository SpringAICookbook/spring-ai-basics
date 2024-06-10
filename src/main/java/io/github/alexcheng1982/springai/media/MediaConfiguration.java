package io.github.alexcheng1982.springai.media;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediaConfiguration {

  @Bean
  public MediaService mediaService(ChatClient.Builder builder) {
    return new MediaService(builder.build());
  }
}
