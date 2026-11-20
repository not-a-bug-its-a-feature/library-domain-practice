package ltwrld.member.application.service;

import com.github.f4b6a3.uuid.UuidCreator;
import ltwrld.member.application.command.CreateMemberTypeCommand;
import ltwrld.member.application.port.out.MemberTypeRepository;
import ltwrld.member.domain.MemberType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberTypeService {
    private final MemberTypeRepository memberTypeRepository;

    public MemberTypeService(MemberTypeRepository memberTypeRepository) {
        this.memberTypeRepository = memberTypeRepository;
    }

    @Transactional
    public void save(CreateMemberTypeCommand command) {
        var memberType = new MemberType(UuidCreator.getTimeOrderedEpoch(), command.name());
        memberTypeRepository.save(memberType);
    }
}
