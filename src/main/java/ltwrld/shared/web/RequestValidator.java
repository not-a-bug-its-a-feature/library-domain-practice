package ltwrld.shared.web;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ltwrld.shared.exception.ErrorCode;
import ltwrld.shared.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * リクエストDTOの有効性を検査
 */
@Component
public class RequestValidator {
    private final Validator validator;

    public RequestValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * リクエストDTOを検証しエラーがある場合は例外
     * @param target　検証対象のDTO
     * @param errorCode　エラー時のエラーコード
     * @param <T>　DTO Type
     * @throws ValidationException 有効性エラーが発生した場合
     */
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
