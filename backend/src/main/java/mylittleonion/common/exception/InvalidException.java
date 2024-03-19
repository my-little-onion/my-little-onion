package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class InvalidException extends MyLittleOnionBaseException {
  protected InvalidException(ErrorBase errorBase) {
    super(errorBase);
  }
}
