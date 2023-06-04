package bootcamps.turkcell.common.utilities.exceptions.business;

import bootcamps.turkcell.common.utilities.exceptions.base.ExceptionResult;
import lombok.AllArgsConstructor;

public class BusinessException extends RuntimeException {
    private ExceptionResult exceptionResult;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ExceptionResult exceptionResult) {
        this.exceptionResult = exceptionResult;
    }

    public BusinessException(String message, ExceptionResult exceptionResult) {
        super(message);
        this.exceptionResult = exceptionResult;
    }
}
