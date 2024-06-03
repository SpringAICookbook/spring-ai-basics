package io.github.alexcheng1982.springai.observation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;

public class LoggingChatClient implements ChatClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      "ChatClient.debugger");

  private final ObjectMapper objectMapper = new ObjectMapper()
      .registerModules(new Jdk8Module(), new JavaTimeModule())
      .enable(SerializationFeature.INDENT_OUTPUT);

  private final ChatClient delegate;

  public LoggingChatClient(ChatClient delegate) {
    this.delegate = delegate;
  }

  @Override
  public ChatResponse call(Prompt prompt) {
    debug(prompt);
    var response = delegate.call(prompt);
    debug(response);
    return response;
  }

  void debug(Object value) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(
          """
              ===========================
              {}:
              {}
              ===========================
              """, value.getClass().getSimpleName(), writeAsString(value));
    }
  }

  String writeAsString(Object input) {
    try {
      return objectMapper.writeValueAsString(input);
    } catch (JsonProcessingException e) {
      return "";
    }
  }
}
