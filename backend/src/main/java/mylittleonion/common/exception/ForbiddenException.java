package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class ForbiddenException extends MyLittleOnionBaseException {
  public ForbiddenException(ErrorBase errorBase) {
    super(errorBase);
  }
}
