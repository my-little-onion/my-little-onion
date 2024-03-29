package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class TooManyRequestException extends MyLittleOnionBaseException {

  public TooManyRequestException(ErrorBase errorBase) {
    super(errorBase);
  }
}
