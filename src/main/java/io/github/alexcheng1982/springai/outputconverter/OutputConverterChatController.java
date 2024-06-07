package io.github.alexcheng1982.springai.outputconverter;

import io.github.alexcheng1982.springai.ChatRequest;
import io.github.alexcheng1982.springai.outputparser.OutputParserChatService;
import io.github.alexcheng1982.springai.outputparser.Users;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/output_converter")
public class OutputConverterChatController {

  private final OutputConverterChatService chatService;

  public OutputConverterChatController(OutputConverterChatService chatService) {
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
