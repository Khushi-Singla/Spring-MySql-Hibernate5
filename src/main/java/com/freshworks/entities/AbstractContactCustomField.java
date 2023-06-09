package com.freshworks.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@MappedSuperclass
public abstract class AbstractContactCustomField {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    protected Long id;

    @Column(name = "contact_form_id")
    @Setter
    private Long contactFormId;

    
    @Column(name = "account_id", nullable = false)
    @Setter
    private Long accountId;
}
