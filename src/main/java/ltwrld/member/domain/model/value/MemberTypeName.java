package ltwrld.member.domain.model.value;

import ltwrld.shared.domain.annotation.Vo;

@Vo
public record MemberTypeName(String value) {
    public static final int MAX_LENGTH = 100;
    public static final String NULL_MESSAGE = "Member Type name must not be blank";
    public static final String TOO_LONG_MESSAGE =
            "Member Type name max length " + MAX_LENGTH;

    public MemberTypeName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(TOO_LONG_MESSAGE);
        }
    }
}
