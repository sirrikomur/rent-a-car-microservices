package bootcamps.turkcell.common.configurations.exception;


import bootcamps.turkcell.common.utilities.constants.ExceptionDetail;
import bootcamps.turkcell.common.utilities.exceptions.base.ExceptionResult;
import bootcamps.turkcell.common.utilities.exceptions.business.BusinessException;
import bootcamps.turkcell.common.utilities.exceptions.business.BusinessExceptionResult;
import bootcamps.turkcell.common.utilities.exceptions.validation.ValidationExceptionResult;
import feign.FeignException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public BusinessExceptionResult handleBusinessException(BusinessException exception) {
        return new BusinessExceptionResult(ExceptionDetail.Types.BUSINESS_EXCEPTION, exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResult handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ExceptionResult(ExceptionDetail.Types.ILLEGAL_ARGUMENT_EXCEPTION, exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResult handleInternalServerError(FeignException.InternalServerError exception) {
        return new ExceptionResult(ExceptionDetail.Types.INTERNAL_SERVER_ERROR, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionResult handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ValidationExceptionResult(ExceptionDetail.Types.VALIDATION_EXCEPTION, validationErrors, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResult handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return new ExceptionResult(ExceptionDetail.Types.DATA_INTEGRITY_VIOLATION_EXCEPTION, exception.getMessage(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResult handleRuntimeException(RuntimeException exception) {
        return new ExceptionResult(ExceptionDetail.Types.RUNTIME_EXCEPTION, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
