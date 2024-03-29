package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class MethodNotAllowedException extends MyLittleOnionBaseException {

  public MethodNotAllowedException(ErrorBase errorBase) {
    super(errorBase);
  }
}
