package mylittleonion.chatGPT.dto;

import com.nimbusds.jose.shaded.gson.Gson;
import mylittleonion.api.voice.dto.ChatGPTResponse;

public class ChatGPTResponseParser {

  public static ChatGPTResponse parse(String result) {
    String json = result
        .replace("primary :", "\"primary\":")
        .replace("secondary :", "\"secondary\":")
        .replace("tertiary :", "\"tertiary\":")
        .replaceAll("\\s*,\\s*", "\",\"")
        .replace("\n", "")
        .trim();

    json = "{" + json + "}";

    Gson gson = new Gson();

    return gson.fromJson(json, ChatGPTResponse.class);
  }

}
