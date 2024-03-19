package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class NotFoundException extends MyLittleOnionBaseException{
  protected NotFoundException(ErrorBase errorBase) {
    super(errorBase);
  }
}
