package io.github.alexcheng1982.springai.streaming;

import io.github.alexcheng1982.springai.ChatRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/streaming")
public class StreamingChatController {

  private final StreamingChatService chatService;

  public StreamingChatController(StreamingChatService chatService) {
    this.chatService = chatService;
  }

  @PostMapping("/chat")
  public ResponseEntity<Void> chatInput(@RequestBody ChatRequest request) {
    chatService.chat(request.input());
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/stop")
  public ResponseEntity<Void> stopGeneration() {
    chatService.stopGeneration();
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/chatResponse",
      produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<String>> streamingChat() {
    return chatService.getChatResponses()
        .map(response -> ServerSentEvent.<String>builder()
            .event("message")
            .data(response.getResult().getOutput().getContent())
            .build());
  }
}
