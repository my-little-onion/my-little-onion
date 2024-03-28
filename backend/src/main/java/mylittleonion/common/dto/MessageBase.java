package mylittleonion.common.dto;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum MessageBase {
  /**
   * 200 OK (성공한 요청)
   */
  S200(OK, false, "MS000", "성공");

  /**
   * 400 Bad Request (잘못된 요청)
   */

  /**
   * 404 Not Found (존재하지 않는 리소스)
   */

  private final HttpStatusCode statusCode;
  private final boolean sendNotification;
  private final String code;
  private final String message;

  MessageBase(HttpStatusCode statusCode, boolean sendNotification, String code, String message) {
    this.statusCode = statusCode;
    this.sendNotification = sendNotification;
    this.code = code;
    this.message = message;
  }

  public int getStatus() {
    return statusCode.value();
  }
}
