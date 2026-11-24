package ltwrld.member.domain.model;

import com.github.f4b6a3.uuid.UuidCreator;
import ltwrld.member.domain.model.value.MemberTypeName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberTypeTest {
    @Test
    void createMemberType() {
        var memberType = new MemberType(
                UuidCreator.getTimeOrderedEpoch(),
                new MemberTypeName("GENERAL")
        );

        assertThat(memberType.name().value()).isEqualTo("GENERAL");
    }
}
