package com.freshworks.entities;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_emails")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContactEmailEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;


    @Column(name = "email")
    private String email;

    @Column(name = "is_primary")
    private Boolean isPrimary = Boolean.FALSE;

    @Column(name = "unsubscribe")
    private Boolean unsubscribe = Boolean.FALSE;

    @Column(name = "email_status")
    private Integer emailStatus;

    @Column(name = "label_id")
    private Long labelId;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "account_id", referencedColumnName = "account_id"),
        @JoinColumn(name = "contact_id", referencedColumnName = "id")
    })
    @JsonBackReference
    private SalesContactEntity salesContact;

    public void setSalesContact(SalesContactEntity contact) {
        salesContact = contact;
    }
}
