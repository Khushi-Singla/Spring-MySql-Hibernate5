package com.freshworks.entities;

import java.time.Instant;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@Table(name = "entity_tag_associations")
@DiscriminatorValue("Contact")
@NoArgsConstructor
@Where(clause = "targetable_type='Contact'")
public class ContactTagAssociation extends EntityTagAssociation{

    @Builder
    public ContactTagAssociation(Long id, Long tagId, Instant createdAt,
                                 Instant updatedAt, SalesContactEntity contactTagAssociations) {
        super(id, tagId, createdAt, updatedAt);
        this.contactTagAssociations = contactTagAssociations;
    }

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
        @JoinColumn(name = "targetable_id", referencedColumnName = "id", insertable = false, updatable = false)
    })
    @JsonBackReference
    private SalesContactEntity contactTagAssociations;
}
