package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class NotImplementedException extends MyLittleOnionBaseException {

  protected NotImplementedException(ErrorBase errorBase) {
    super(errorBase);
  }
}
