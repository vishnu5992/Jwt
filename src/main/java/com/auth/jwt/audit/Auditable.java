package com.auth.jwt.audit;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Data
@EqualsAndHashCode
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U>
{

    @CreatedBy
    @Column(name="created_by", updatable = false)
    protected U createdBy;

    @Column(name = "created_date", updatable = false)
    @Temporal(TIMESTAMP)
    @CreatedDate
    protected LocalDateTime creationDate;

    @LastModifiedBy
    @Column(name="modified_by")
    protected U lastModifiedBy;

    @Column(name = "lastMod_date")
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected LocalDateTime lastModifiedDate;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    protected Boolean isDeleted = false;


}
