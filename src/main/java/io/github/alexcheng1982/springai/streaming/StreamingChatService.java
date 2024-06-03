package io.github.alexcheng1982.springai.streaming;

import java.util.concurrent.atomic.AtomicReference;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.StreamingChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class StreamingChatService {

  private final AtomicReference<ChatResponseHolder> chatResponseHolder = new AtomicReference<>();

  private final Flux<ChatResponse> chatResponses;
  private FluxSink<String> fluxSink;

  public StreamingChatService(StreamingChatClient chatClient) {
    Flux<String> chatInputs = Flux.create(sink -> fluxSink = sink);
    chatResponses = chatInputs.switchMap(
        input -> {
          var holder = new ChatResponseHolder(
              chatClient.stream(new Prompt(input)));
          chatResponseHolder.set(holder);
          return holder.chatResponse();
        }
    );
  }

  public void chat(String input) {
    fluxSink.next(input);
  }

  public void stopGeneration() {
    if (chatResponseHolder.get() != null) {
      chatResponseHolder.get().stop();
    }
  }

  public Flux<ChatResponse> getChatResponses() {
    return chatResponses;
  }
}
