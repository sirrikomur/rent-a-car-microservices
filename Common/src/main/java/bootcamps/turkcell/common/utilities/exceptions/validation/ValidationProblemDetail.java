package bootcamps.turkcell.common.utilities.exceptions.validation;

import bootcamps.turkcell.common.utilities.exceptions.base.ProblemDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValidationProblemDetail extends ProblemDetail {
    public ValidationProblemDetail(String type, Object message, int code) {
        super(type, message, code);
    }
}

