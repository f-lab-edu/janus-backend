package kr.ai.janus.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@MappedSuperclass
public abstract class AuditedEntity extends CreatedAtEntity {

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    protected OffsetDateTime updatedAt;

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
