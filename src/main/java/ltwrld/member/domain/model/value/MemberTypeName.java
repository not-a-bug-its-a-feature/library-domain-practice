package ltwrld.member.domain.model.value;

import ltwrld.shared.domain.annotation.Vo;

@Vo
public record MemberTypeName(String value) {
    public static final int MAX_LENGTH = 100;
    public static final String REQUIRED_MESSAGE = "会員種別名は空白にできません。";
    public static final String TOO_LONG_MESSAGE =
            "会員種別名は" + MAX_LENGTH + "文字以内で入力してください。";

    public MemberTypeName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(REQUIRED_MESSAGE);
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(TOO_LONG_MESSAGE);
        }
    }
}
