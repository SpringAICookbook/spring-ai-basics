package io.github.alexcheng1982.springai.response;

public record Response(String output, TokenUsage tokenUsage) {
  public record TokenUsage(Long promptTokens, Long generationTokens) {

  }
}
