package mylittleonion.common.exception;

import lombok.Getter;
import mylittleonion.common.dto.ErrorBase;

@Getter
public abstract class MyLittleOnionBaseException extends RuntimeException{
  private final ErrorBase errorBase;

  protected MyLittleOnionBaseException(ErrorBase errorBase) {
    super(errorBase.getMessage());
    this.errorBase = errorBase;
  }
}
