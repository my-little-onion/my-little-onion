package mylittleonion.common.exception;

import mylittleonion.common.dto.ErrorBase;

public class UnsupportedMediaTypeException extends MyLittleOnionBaseException {

  public UnsupportedMediaTypeException(ErrorBase errorBase) {
    super(errorBase);
  }
}
