package io.github.alexcheng1982.springai.streaming;

import org.springframework.ai.chat.ChatResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public record ChatResponseHolder(Flux<ChatResponse> response,
                                 Sinks.One<Boolean> stopSignal) {

  public ChatResponseHolder(Flux<ChatResponse> response) {
    this(response, Sinks.one());
  }

  public Flux<ChatResponse> chatResponse() {
    return response.takeUntilOther(stopSignal.asMono());
  }

  public void stop() {
    stopSignal.tryEmitValue(true);
  }
}
