package ltwrld.member.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberTypeRequest(
        @NotBlank
        @Size(max = 100)
        String name
) {

}
