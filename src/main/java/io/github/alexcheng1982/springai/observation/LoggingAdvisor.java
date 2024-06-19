package io.github.alexcheng1982.springai.observation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.AdvisedRequest;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

public class LoggingAdvisor implements RequestResponseAdvisor {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      "ChatClient.debugger");

  private final ObjectMapper objectMapper = new ObjectMapper()
      .registerModules(new Jdk8Module(), new JavaTimeModule())
      .enable(SerializationFeature.INDENT_OUTPUT)
      .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

  @Override
  public AdvisedRequest adviseRequest(AdvisedRequest request,
      Map<String, Object> context) {
    debug(request, context);
    return request;
  }

  @Override
  public ChatResponse adviseResponse(ChatResponse response,
      Map<String, Object> context) {
    debug(response, context);
    return response;
  }

  @Override
  public Flux<ChatResponse> adviseResponse(Flux<ChatResponse> fluxResponse,
      Map<String, Object> context) {
    return fluxResponse.doOnEach(response -> debug(response, context));
  }

  private void debug(Object value, Map<String, Object> context) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(
          """
              ===========================
              {}: {}
              Context: {}
              ===========================
              """, value.getClass().getSimpleName(), writeAsString(value),
          context);
    }
  }

  private String writeAsString(Object input) {
    try {
      return objectMapper.writeValueAsString(input);
    } catch (JsonProcessingException e) {
      return "";
    }
  }
}
