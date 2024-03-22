package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class MethodNotAllowedException extends MyLittleOnionBaseException {

  protected MethodNotAllowedException(ErrorBase errorBase) {
    super(errorBase);
  }
}
