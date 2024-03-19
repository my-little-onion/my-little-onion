package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class NotAcceptableException extends MyLittleOnionBaseException{
  protected NotAcceptableException(ErrorBase errorBase) {
    super(errorBase);
  }
}
