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
@NoArgsConstructor
@DiscriminatorValue("SalesAccount")
@Where(clause = "targetable_type='SalesAccount'")
public class AccountTagAssociation extends EntityTagAssociation{



    @Builder
    public AccountTagAssociation(Long id, Long tagId, Instant createdAt,
                                 Instant updatedAt, SalesAccount accountTagAssociations) {
        super(id, tagId, createdAt, updatedAt);
        this.accountTagAssociations = accountTagAssociations;
    }

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "account_id", referencedColumnName = "account_id"),
        @JoinColumn(name = "targetable_id", referencedColumnName = "id")
    })
    @JsonBackReference
    private SalesAccount accountTagAssociations;
}
