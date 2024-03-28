package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class DuplicateException extends MyLittleOnionBaseException {

  public DuplicateException(ErrorBase errorBase) {
    super(errorBase);
  }
}
