package ltwrld.member.adapter.in.web;

import jakarta.servlet.ServletException;
import ltwrld.member.adapter.in.web.dto.MemberTypeRequest;
import ltwrld.member.application.command.CreateMemberTypeCommand;
import ltwrld.member.application.service.MemberTypeService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;

@Component
public class MemberTypeHandler {
    private final MemberTypeService memberTypeService;

    public MemberTypeHandler(MemberTypeService memberTypeService) {
        this.memberTypeService = memberTypeService;
    }

    public ServerResponse createMemberType(ServerRequest request) throws ServletException, IOException {
        MemberTypeRequest memberTypeRequest = request.body(MemberTypeRequest.class);

        memberTypeService.save(new CreateMemberTypeCommand(memberTypeRequest.name()));

        return ServerResponse.ok().build();
    }
}
