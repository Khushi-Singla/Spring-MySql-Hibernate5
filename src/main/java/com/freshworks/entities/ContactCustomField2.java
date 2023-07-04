package com.freshworks.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_custom_field2")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@NoArgsConstructor
public class ContactCustomField2
        extends AbstractContactCustomField implements Serializable {
    @Builder
    public ContactCustomField2(Long id, Long contactFormId,  Long accountId) {
        super(id, contactFormId, accountId);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private SalesContact contact;

    public void setSalesContact(SalesContact salesContact) {
        contact = salesContact;
    }
}
