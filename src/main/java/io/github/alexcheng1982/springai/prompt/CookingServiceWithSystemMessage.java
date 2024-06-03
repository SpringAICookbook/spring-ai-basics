package io.github.alexcheng1982.springai.prompt;

import java.util.List;
import java.util.Map;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

public class CookingServiceWithSystemMessage {

  private final ChatClient chatClient;

  @Value("classpath:/prompts/cooking.st")
  private Resource promptResource;

  public CookingServiceWithSystemMessage(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String chat(String dish) {
    Message message = new PromptTemplate(promptResource).createMessage(
        Map.of("dish", dish)
    );
    Prompt prompt = new Prompt(List.of(
        new SystemMessage("You are a master chef."),
        message
    ));
    return chatClient.call(prompt).getResult().getOutput().getContent();
  }
}
