package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class UnAuthorizedException extends MyLittleOnionBaseException{
  protected UnAuthorizedException(ErrorBase errorBase) {
    super(errorBase);
  }
}
