package io.github.alexcheng1982.springai.media;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

public class MediaService {

  private final ChatClient chatClient;

  @Value("classpath:/media/image.jpg")
  private Resource imageResource;

  public MediaService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String imageUnderstanding() {
    return chatClient.prompt()
        .options(OllamaOptions.create().withModel("llava"))
        .user(promptUserSpec ->
            promptUserSpec.text("What's in this picture?")
                .media(MediaType.IMAGE_JPEG, imageResource))
        .call()
        .content();
  }
}
