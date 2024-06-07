package io.github.alexcheng1982.springai.quickstart;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  private final ChatClient chatClient;

  public ChatController(ChatClient.Builder builder) {
    this.chatClient = builder.build();
  }

  @GetMapping("/chat")
  public String chat(@RequestParam(value = "message") String message) {
    return chatClient.prompt().user(message).call().content();
  }
}
