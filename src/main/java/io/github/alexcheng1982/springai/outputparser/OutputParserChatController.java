package io.github.alexcheng1982.springai.outputparser;

import io.github.alexcheng1982.springai.ChatRequest;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/output_parser")
public class OutputParserChatController {

  private final OutputParserChatService chatService;

  public OutputParserChatController(OutputParserChatService chatService) {
    this.chatService = chatService;
  }

  @PostMapping("/list")
  public List<String> listOutput(@RequestBody ChatRequest request) {
    return chatService.listOutput(request.input());
  }

  @PostMapping("/map")
  public Map<String, Object> mapOutput(@RequestBody ChatRequest request) {
    return chatService.mapOutput(request.input());
  }

  @PostMapping("/bean")
  public Users beanOutput() {
    return chatService.beanOutput();
  }
}
