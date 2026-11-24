package ltwrld.member.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ltwrld.member.domain.model.value.MemberTypeName;
import ltwrld.shared.web.annotation.Dto;

@Dto
public record MemberTypeRequest(
        @NotBlank(message = MemberTypeName.NULL_MESSAGE)
        @Size(
                max = MemberTypeName.MAX_LENGTH,
                message = MemberTypeName.TOO_LONG_MESSAGE
        )
        String name
) {
}
