package io.github.alexcheng1982.springai.prompt;

import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaOptions;

public class CustomChatOptions {

  private final ChatClient chatClient;

  public CustomChatOptions(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String chat(String input) {
    Prompt prompt = new Prompt(input, OllamaOptions.create()
        .withTemperature(0.1f)
        .withStop(List.of("Observation:")));
    return chatClient.prompt(prompt).call().content();
  }
}
