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
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.validator.cdi.HibernateValidator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_emails")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ContactEmail
        implements Serializable {
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

    @Column(name = "account_id", nullable = false)
    @Setter
    private Long accountId;

    @Valid
    @Column(name = "email")
    @Pattern(regexp = "^$|(\\b[-a-zA-Z0-9.'’&_%+]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,20}\\b)",
            message = "Work email format is not valid.")
    private String email;

    @Column(name = "is_primary")
    private Boolean isPrimary = Boolean.FALSE;

    @Column(name = "unsubscribe")
    private Boolean unsubscribe = Boolean.FALSE;

    @Column(name = "email_status")
    private Integer emailStatus;

    @Column(name = "label_id")
    private Long labelId;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
//    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
//    @LazyToOne(LazyToOneOption.PROXY)
//    @JsonBackReference
//    private SalesContact contact;
//
//    public void setContact(SalesContact salesContact) {
//        this.contact = salesContact;
//    }
}
