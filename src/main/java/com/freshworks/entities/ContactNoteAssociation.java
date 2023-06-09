package com.freshworks.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@DiscriminatorValue("Contact")
@NoArgsConstructor
@Where(clause = "targetable_type='Contact'")
public class ContactNoteAssociation
        extends EntityNoteAssociation implements Serializable {
    @Builder
    public ContactNoteAssociation(Long id, Long noteId, Instant createdAt,
                                  Instant updatedAt, SalesContact contact) {
        super(id, noteId, createdAt, updatedAt);
        this.contact = contact;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "account_id",insertable = false,updatable = false)
    @JoinColumn(name = "targetable_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private SalesContact contact;

    public void setContact(SalesContact contact) {
        this.contact = contact;
    }
}
