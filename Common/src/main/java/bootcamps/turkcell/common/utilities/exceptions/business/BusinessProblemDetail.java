package bootcamps.turkcell.common.utilities.exceptions.business;


import bootcamps.turkcell.common.utilities.exceptions.base.ProblemDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessProblemDetail extends ProblemDetail {

    public BusinessProblemDetail(String type, Object message, int code) {
        super(type, message, code);
    }
}


