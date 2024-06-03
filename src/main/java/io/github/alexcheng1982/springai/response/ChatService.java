package io.github.alexcheng1982.springai.response;

import io.github.alexcheng1982.springai.response.Response.TokenUsage;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.prompt.Prompt;

public class ChatService {
  private final ChatClient chatClient;

  public ChatService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public Response chat(String input) {
    ChatResponse response = chatClient.call(new Prompt(input));
    String content = response.getResult().getOutput().getContent();
    Usage usage = response.getMetadata().getUsage();
    TokenUsage tokenUsage = null;
    if (usage != null) {
      tokenUsage = new TokenUsage(usage.getPromptTokens(),
          usage.getGenerationTokens());
    }
    return new Response(content, tokenUsage);
  }
}
