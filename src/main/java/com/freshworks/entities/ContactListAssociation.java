package com.freshworks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_list_associations")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContactListAssociation
        implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    
    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Instant createdAt;

    
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    
    @Column(name = "list_id", nullable = false)
    private Long listId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private SalesContact contact;

    public void setContact(SalesContact contact) {
        this.contact = contact;
    }
}
