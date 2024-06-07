package io.github.alexcheng1982.springai.prompt;

import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;

public class JavaProgrammingChatService {

  private final ChatClient chatClient;

  public JavaProgrammingChatService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String chat(String input) {
    Prompt prompt = new Prompt(
        List.of(
            new SystemMessage("You are a Java programming expert."),
            new UserMessage(input)
        )
    );
    return chatClient.prompt(prompt).call().content();
  }
}
