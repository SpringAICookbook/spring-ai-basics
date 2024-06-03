package io.github.alexcheng1982.springai.outputparser;

import java.util.List;
import java.util.Map;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.ai.parser.ListOutputParser;
import org.springframework.ai.parser.MapOutputParser;

public class OutputParserChatService {

  private final ChatClient chatClient;
  private final ListOutputParser listOutputParser;
  private final MapOutputParser mapOutputParser;

  public OutputParserChatService(ChatClient chatClient,
      ListOutputParser listOutputParser,
      MapOutputParser mapOutputParser) {
    this.chatClient = chatClient;
    this.listOutputParser = listOutputParser;
    this.mapOutputParser = mapOutputParser;
  }

  public List<String> listOutput(String input) {
    var promptTemplate = new PromptTemplate("""
        {input}
                
        {format}
        """);
    var response = chatClient.call(promptTemplate.create(
        Map.of(
            "input", input,
            "format", listOutputParser.getFormat()
        )));
    return listOutputParser.parse(
        response.getResult().getOutput().getContent());
  }

  public Map<String, Object> mapOutput(String input) {
    var promptTemplate = new PromptTemplate("""
        {input}
                
        {format}
        """);
    var response = chatClient.call(promptTemplate.create(
        Map.of(
            "input", input,
            "format", mapOutputParser.getFormat()
        )));
    var content = normalizeJsonOutput(
        response.getResult().getOutput().getContent());
    return mapOutputParser.parse(content);
  }

  public Users beanOutput() {
    var outputParser = new BeanOutputParser<>(Users.class);
    var promptTemplate = new PromptTemplate("""
        Generate a list of 10 User objects.
                
        {format}
        """);
    var response = chatClient.call(promptTemplate.create(
        Map.of(
            "format", outputParser.getFormat()
        )));
    var content = normalizeJsonOutput(
        response.getResult().getOutput().getContent());
    return outputParser.parse(content);
  }

  private String normalizeJsonOutput(String content) {
    return content.replaceAll("`{3}(?:json)?", "");
  }
}
