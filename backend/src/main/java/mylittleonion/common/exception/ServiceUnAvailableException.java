package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class ServiceUnAvailableException extends MyLittleOnionBaseException{
  protected ServiceUnAvailableException(ErrorBase errorBase) {
    super(errorBase);
  }
}
