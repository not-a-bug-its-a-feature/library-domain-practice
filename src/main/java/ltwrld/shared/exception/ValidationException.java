package ltwrld.shared.exception;

import java.util.List;
import java.util.Map;

public class ValidationException extends DomainException{
    private final ErrorCode errorCode;
    private final Map<String, List<String>> errors;

    public ValidationException(
            ErrorCode errorCode,
            Map<String, List<String>> errors
    ) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.errors = errors == null ? Map.of() : Map.copyOf(errors);
    }

    @Override
    public ErrorCode errorCode() {
        return errorCode;
    }

    public Map<String, List<String>> errors() {
        return errors;
    }
}
