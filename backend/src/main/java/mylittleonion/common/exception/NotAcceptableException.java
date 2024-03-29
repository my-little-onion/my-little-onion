package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class NotAcceptableException extends MyLittleOnionBaseException {

  public NotAcceptableException(ErrorBase errorBase) {
    super(errorBase);
  }
}
