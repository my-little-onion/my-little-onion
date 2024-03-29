package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class NotImplementedException extends MyLittleOnionBaseException {

  public NotImplementedException(ErrorBase errorBase) {
    super(errorBase);
  }
}
