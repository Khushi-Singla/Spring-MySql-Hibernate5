package com.freshworks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_custom_fields")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class ContactCustomField
        extends AbstractContactCustomField implements Serializable  {
    @Builder
    @SuppressWarnings("squid:S107")
    public ContactCustomField(Long id, Long contactFormId,  Long accountId,
                              Instant createdAt, Instant updatedAt) {
        super(id, contactFormId, accountId);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    
    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Instant createdAt;

    
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private SalesContact contact;

    public void setSalesContact(SalesContact salesContact) {
        contact = salesContact;
    }
}
