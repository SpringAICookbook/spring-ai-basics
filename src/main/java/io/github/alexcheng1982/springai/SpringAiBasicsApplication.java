package io.github.alexcheng1982.springai;

import io.github.alexcheng1982.springai.outputconverter.OutputConverterConfiguration;
import io.github.alexcheng1982.springai.outputparser.OutputParserConfiguration;
import io.github.alexcheng1982.springai.prompt.PromptConfiguration;
import io.github.alexcheng1982.springai.streaming.StreamingChatConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({PromptConfiguration.class,
    OutputParserConfiguration.class,
    OutputConverterConfiguration.class,
    StreamingChatConfiguration.class})
public class SpringAiBasicsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAiBasicsApplication.class, args);
  }
}
