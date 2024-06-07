package io.github.alexcheng1982.springai.outputconverter;

import io.github.alexcheng1982.springai.outputparser.Users;
import java.util.List;
import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;

public class OutputConverterChatService {

  private final ChatClient chatClient;
  private final ListOutputConverter listOutputConverter;
  private final MapOutputConverter mapOutputConverter;

  public OutputConverterChatService(ChatClient chatClient,
      ListOutputConverter listOutputConverter,
      MapOutputConverter mapOutputConverter) {
    this.chatClient = chatClient;
    this.listOutputConverter = listOutputConverter;
    this.mapOutputConverter = mapOutputConverter;
  }

  public List<String> listOutput(String input) {
    var promptTemplate = new PromptTemplate("""
        {input}
                
        {format}
        """);
    var response = chatClient.prompt(promptTemplate.create(
        Map.of(
            "input", input,
            "format", listOutputConverter.getFormat()
        )));
    return listOutputConverter.convert(response.call().content());
  }

  public Map<String, Object> mapOutput(String input) {
    var promptTemplate = new PromptTemplate("""
        {input}
                
        {format}
        """);
    var response = chatClient.prompt(promptTemplate.create(
        Map.of(
            "input", input,
            "format", mapOutputConverter.getFormat()
        )));
    return mapOutputConverter.convert(response.call().content());
  }

  public Users beanOutput() {
    var outputParser = new BeanOutputConverter<>(Users.class);
    var promptTemplate = new PromptTemplate("""
        Generate a list of 10 User objects.
                
        {format}
        """);
    var response = chatClient.prompt(promptTemplate.create(
        Map.of(
            "format", outputParser.getFormat()
        )));
    return outputParser.convert(response.call().content());
  }

  public List<String> listOutput2(String input) {
    return chatClient.prompt()
        .user(input)
        .call()
        .entity(listOutputConverter);
  }

  public Map<String, Object> mapOutput2(String input) {
    return chatClient.prompt()
        .user(input)
        .call()
        .entity(mapOutputConverter);
  }

  public Map<String, Object> mapOutput3(String input) {
    return chatClient.prompt()
        .user(input)
        .call()
        .entity(new ParameterizedTypeReference<>() {
        });
  }

  public Users beanOutput2() {
    return chatClient.prompt()
        .user("Generate a list of 10 User objects.")
        .call()
        .entity(Users.class);
  }
}
