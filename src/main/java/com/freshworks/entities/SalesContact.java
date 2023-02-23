package com.freshworks.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contacts")
@javax.persistence.Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Contact")
public class SalesContact
        implements Serializable {
    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "contactTagAssociations", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ContactTagAssociation> tags;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ContactEmail> emails;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false),
        @JoinColumn(name = "id", referencedColumnName = "contact_id", insertable = false, updatable = false)
    })
    private ContactCustomField contactCustomField;

    public void addEmail(ContactEmail email){
        if(email!=null){
            emails = emails==null ? new ArrayList<>() : emails;
            emails.add(email);
        }
    }
    public void removeEmail(ContactEmail email){
        if(email!=null){
            emails.removeIf(e -> e.getEmail().equals(email.getEmail()));
        }
    }
}
