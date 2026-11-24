package ltwrld.shared.web;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ltwrld.shared.exception.ErrorCode;
import ltwrld.shared.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RequestValidator {
    private final Validator validator;

    public RequestValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> void validate(T target, ErrorCode errorCode) {
        Set<ConstraintViolation<T>> violations = validator.validate(target);
        if (violations.isEmpty()) {
            return;
        }

        Map<String, List<String>> errors = new LinkedHashMap<>();

        for (ConstraintViolation<T> violation : violations) {
            String field = violation.getPropertyPath().toString();
            errors.computeIfAbsent(field, k -> new ArrayList<>())
                    .add(violation.getMessage());
        }

        throw new ValidationException(errorCode, errors);
    }
}
