package bootcamps.turkcell.common.utilities.exceptions.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResult {
    private LocalDateTime timestamp;
    private String type;
    private Object message;
    private int code;

    public ExceptionResult(String type, Object message, int code) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.message = message;
        this.code = code;
    }
}
