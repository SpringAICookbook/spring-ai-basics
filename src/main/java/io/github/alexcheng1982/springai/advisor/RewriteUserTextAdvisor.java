package io.github.alexcheng1982.springai.advisor;

import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.AdvisedRequest;
import org.springframework.ai.chat.client.RequestResponseAdvisor;

public class RewriteUserTextAdvisor implements RequestResponseAdvisor {

  @Override
  public AdvisedRequest adviseRequest(AdvisedRequest request,
      Map<String, Object> context) {
    var updatedUserText = context.get("updated_user_text");
    if (updatedUserText != null) {
      var userText = StringUtils.trimToEmpty(Objects.toString(updatedUserText));
      if (StringUtils.isNotEmpty(userText)) {
        return AdvisedRequest.from(request).withUserText(userText).build();
      }
    }

    return RequestResponseAdvisor.super.adviseRequest(request, context);
  }
}
