package io.github.alexcheng1982.springai.prompt;

import java.util.Map;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

public class CookingService {

  private final ChatClient chatClient;

  @Value("classpath:/prompts/cooking.st")
  private Resource promptResource;

  public CookingService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String chat(String dish) {
    Prompt prompt = new PromptTemplate(promptResource).create(
        Map.of("dish", dish)
    );
    return chatClient.call(prompt).getResult().getOutput().getContent();
  }
}
