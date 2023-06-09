package com.freshworks.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * EntityTypes
 *   Lead: 1,
 *   Contact: 2,
 *   Deal: 3,
 *   SalesAccount: 4
 */
@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@DiscriminatorValue("2")
@NoArgsConstructor
@Where(clause = "entity_type=2")
@EqualsAndHashCode(callSuper = true)
public class ContactSubscription
        extends EntitySubscription implements Serializable {
    @Builder
    public ContactSubscription(Long accountId, Long id, String subscriptionTypes,
                               String unsubscriptionTypes, Instant createdAt, Instant updatedAt, SalesContact contact) {
        super(accountId, id, subscriptionTypes, unsubscriptionTypes, createdAt, updatedAt);
        this.contact = contact;
    }

    @OneToOne
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    @JsonBackReference
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
    }
}
