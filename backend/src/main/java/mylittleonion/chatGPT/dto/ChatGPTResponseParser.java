package mylittleonion.chatGPT.dto;

import com.nimbusds.jose.shaded.gson.Gson;
import mylittleonion.api.voice.dto.ChatGPTResponse;

public class ChatGPTResponseParser {

  public static ChatGPTResponse parse(String result) {
    // JSON 형식에 맞게 문자열 수정
    String json = result.replaceAll("(\\w+)\\s*:", "\"$1\":")
        .replaceAll(":\\s*null", ":\"null\"")
        .replaceAll("\\s*,\\s*", ",")
        .trim();

    // Gson 객체를 사용하여 JSON 문자열을 ChatGPTResponse 객체로 파싱
    Gson gson = new Gson();
    return gson.fromJson(json, ChatGPTResponse.class);
  }

}
