package ltwrld.member.domain.model.error;

import ltwrld.shared.exception.ErrorCode;

public enum MemberErrorCode implements ErrorCode {
    MEMBER_TYPE_INVALID("MEMBER-TYPE-INVALID", "member error")
    ;

    private final String code;
    private final String message;

    MemberErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
