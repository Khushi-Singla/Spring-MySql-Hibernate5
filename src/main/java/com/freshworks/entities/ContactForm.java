package com.freshworks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_forms")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "active")
    private Boolean active;

    @Lob
    @Column(name = "form_options")
    private String formOptions;

    
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "version")
    private Integer version;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "`default`")
    private Boolean defaultField = Boolean.FALSE;

    
    @Column(name = "service_form_id")
    private String serviceFormId;

    @Column(name = "serialized_form")
    private byte[] serializedForm;
}
