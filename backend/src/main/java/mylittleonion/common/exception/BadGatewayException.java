package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class BadGatewayException extends MyLittleOnionBaseException{
  public BadGatewayException(ErrorBase errorBase) {
    super(errorBase);
  }
}