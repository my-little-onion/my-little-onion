package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class InternalServerException extends MyLittleOnionBaseException{

  protected InternalServerException(ErrorBase errorBase) {
    super(errorBase);
  }
}
