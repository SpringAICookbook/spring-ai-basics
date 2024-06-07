package io.github.alexcheng1982.springai.response;

import io.github.alexcheng1982.springai.response.Response.TokenUsage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;

public class ChatService {

  private final ChatClient chatClient;

  public ChatService(ChatClient.Builder builder) {
    this.chatClient = builder.build();
  }

  public Response chat(String input) {
    ChatResponse response = chatClient.prompt().user(input).call()
        .chatResponse();
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
