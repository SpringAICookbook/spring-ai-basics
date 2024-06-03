package io.github.alexcheng1982.springai.prompt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookingController {
  private final CookingService cookingService;

  public CookingController(CookingService cookingService) {
    this.cookingService = cookingService;
  }

  @GetMapping("/cooking")
  public String chat(@RequestParam(value = "dish") String dish) {
    return cookingService.chat(dish);
  }
}
