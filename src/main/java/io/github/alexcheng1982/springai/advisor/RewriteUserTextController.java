package io.github.alexcheng1982.springai.advisor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advisor")
public class RewriteUserTextController {

  private final ChatClient chatClient;

  public RewriteUserTextController(ChatClient.Builder builder) {
    chatClient = builder.defaultAdvisors(new RewriteUserTextAdvisor()).build();
  }

  @PostMapping("/rewriteUserText")
  public String rewriteUserText(@RequestBody RewriteUserTextRequest request) {
    return chatClient.prompt()
        .user(request.input())
        .advisors(
            advisorSpec -> advisorSpec.param("updated_user_text",
                StringUtils.trimToEmpty(request.updatedInput()))).call()
        .content();
  }
}
