package ltwrld.member.domain.model;

import ltwrld.member.domain.model.value.MemberTypeName;

import java.time.Instant;
import java.util.UUID;

public class MemberType {
    private final UUID id;
    private MemberTypeName name;
    private Instant createdAt;
    private Instant updatedAt;

    public MemberType(UUID id, MemberTypeName name) {
        this.id = id;
        this.name = name;
    }

    public void changeName(MemberTypeName name) {
        this.name = name;
    }

    public UUID id() {
        return id;
    }

    public MemberTypeName name() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
