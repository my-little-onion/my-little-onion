package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class UnAuthorizedException extends MyLittleOnionBaseException {

  public UnAuthorizedException(ErrorBase errorBase) {
    super(errorBase);
  }
}
