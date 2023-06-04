package bootcamps.turkcell.common.utilities.exceptions.business;


import bootcamps.turkcell.common.utilities.exceptions.base.ExceptionResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessExceptionResult extends ExceptionResult {

    public BusinessExceptionResult(String type, Object message, int code) {
        super(type, message, code);
    }
}


