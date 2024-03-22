package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class TooManyRequestException extends MyLittleOnionBaseException {

  protected TooManyRequestException(ErrorBase errorBase) {
    super(errorBase);
  }
}
