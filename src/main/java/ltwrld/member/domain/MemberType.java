package ltwrld.member.domain;

import java.time.Instant;
import java.util.UUID;

public class MemberType {
    private final UUID id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    public MemberType(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public UUID id() {
        return id;
    }

    public String name() {
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
