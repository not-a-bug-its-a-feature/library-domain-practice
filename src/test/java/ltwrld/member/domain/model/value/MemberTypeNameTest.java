package ltwrld.member.domain.model.value;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberTypeNameTest {
    @Test
    void shouldCreateWhenNaIsValid() {
        var memberTypeName = new MemberTypeName("test");

        assertThat(memberTypeName.value()).isEqualTo("test");
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThatThrownBy(() -> new MemberTypeName(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberTypeName.REQUIRED_MESSAGE);
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        assertThatThrownBy(() -> new MemberTypeName(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberTypeName.REQUIRED_MESSAGE);

        assertThatThrownBy(() -> new MemberTypeName(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberTypeName.REQUIRED_MESSAGE);
    }

    @Test
    void shouldCreateWhenNameLengthIsMaxLength() {
        var memberTypeName = new MemberTypeName("a".repeat(MemberTypeName.MAX_LENGTH));

        assertThat(memberTypeName.value()).hasSize(MemberTypeName.MAX_LENGTH);
    }

    @Test
    void shouldThrowExceptionWhenNameExceedsMaxLength() {
        var name = "a".repeat(MemberTypeName.MAX_LENGTH + 1);

        assertThatThrownBy(() -> new MemberTypeName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberTypeName.TOO_LONG_MESSAGE);
    }
}
