package ltwrld.member.adapter.in.web;

import jakarta.servlet.ServletException;
import ltwrld.member.adapter.in.web.dto.MemberTypeRequest;
import ltwrld.member.application.command.CreateMemberTypeCommand;
import ltwrld.member.application.service.MemberTypeService;
import ltwrld.member.domain.model.error.MemberErrorCode;
import ltwrld.shared.web.RequestValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;

@Component
public class MemberTypeHandler {
    private final RequestValidator requestValidator;
    private final MemberTypeService memberTypeService;

    public MemberTypeHandler(
            RequestValidator requestValidator,
            MemberTypeService memberTypeService
    ) {
        this.requestValidator = requestValidator;
        this.memberTypeService = memberTypeService;
    }

    public ServerResponse createMemberType(ServerRequest request) throws ServletException, IOException {
        MemberTypeRequest body = request.body(MemberTypeRequest.class);

        requestValidator.validate(body, MemberErrorCode.MEMBER_TYPE_INVALID);

        memberTypeService.save(new CreateMemberTypeCommand(body.name()));

        return ServerResponse.ok().build();
    }
}
