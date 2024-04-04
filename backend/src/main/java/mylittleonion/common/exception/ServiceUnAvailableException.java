package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class ServiceUnAvailableException extends MyLittleOnionBaseException {

  public ServiceUnAvailableException(ErrorBase errorBase) {
    super(errorBase);
  }
}
