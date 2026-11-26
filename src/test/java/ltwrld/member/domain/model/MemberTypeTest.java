package ltwrld.member.domain.model;

import com.github.f4b6a3.uuid.UuidCreator;
import ltwrld.member.domain.model.value.MemberTypeName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberTypeTest {
    private UUID id;
    private MemberTypeName name;
    private MemberType memberType;

    @BeforeEach
    void setUp() {
        id = UuidCreator.getTimeOrderedEpoch();
        name = new MemberTypeName("GENERAL");
        memberType = new MemberType(id, name);
    }
    @Test
    void shouldCreateMemberType() {

        assertThat(memberType.id()).isEqualTo(id);
        assertThat(memberType.name().value()).isEqualTo(name.value());
    }

    @Test
    void shouldChangeName() {
        var newName = new MemberTypeName("VIP");

        memberType.changeName(newName);

        assertThat(memberType.name().value()).isEqualTo(newName.value());
    };

    @Test
    void shouldKeepIdWhenNameChange() {
        var newName = new MemberTypeName("VIP");

        memberType.changeName(newName);

        assertThat(memberType.id()).isEqualTo(id);
    }
}
