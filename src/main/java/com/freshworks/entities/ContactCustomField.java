package com.freshworks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_custom_fields")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContactCustomField
        implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contact_form_id")
    private Long contactFormId;
//
//    @Column(name = "contact_id")
//    private Long contactId;
//
//    @Column(name = "account_id")
//    private Long accountId;

    @Column(name = "cf_str01")
    private String cfStr01;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "account_id", referencedColumnName = "account_id"),
        @JoinColumn(name = "contact_id", referencedColumnName = "id")
    })
    @JsonBackReference
    private SalesContact customFields;

    public void setSalesContact(SalesContact salesContact) {
        customFields = salesContact;
    }
}











