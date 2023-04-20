package com.freshworks.controller;

import java.time.Instant;

import com.freshworks.entities.ContactEmailEntity;
import com.freshworks.entities.SalesContactEntity;
import com.freshworks.repository.ContactEmailRepository;
import com.freshworks.repository.SalesContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/contacts")
public class SalesContactsController{
    @Autowired
    private SalesContactRepository salesContactRepository;

    @Autowired
    private ContactEmailRepository contactEmailRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<SalesContactEntity> getAllContacts() {
        return salesContactRepository.findAllByAccountId(10101010L);
    }

    @GetMapping(path="/{id}/get")
    public @ResponseBody SalesContactEntity getContact(@PathVariable Long id) {
        return salesContactRepository.findByIdAndAccountId(id, 10101010L).get();
    }

    @PutMapping(path="/{id}/update")
    public @ResponseBody SalesContactEntity updateContact(@PathVariable Long id) {
        //Contact
        //Contact Custom Field
        //Contact Custom Field2
        //Contact Email
        //Contact Phone number
        SalesContactEntity contact = salesContactRepository.findByIdAndAccountId(id, 10101010L).get();
        ContactEmailEntity contactEmail = ContactEmailEntity.builder().email("1@xyz.com")
            .createdAt(Instant.now()).updatedAt(Instant.now()).build();
//        ContactEmail saved = contactEmailRepository.save(contactEmail);
        contact.addContactEmail(contactEmail);
        return salesContactRepository.save(contact);
    }
    @PostMapping(path="/postEntity")
    public void postEntity() {
       insertContactAndEmail();
//        insertContactAndCustomField();
    }

    @DeleteMapping(path = "/delete")
    public void deleteEntity() {
        deleteSalesContact();
    }

    public SalesContactEntity insertContactAndEmail() {
        ContactEmailEntity contactEmail1 = ContactEmailEntity.builder().email("xpqr@gmail.com").build();
        SalesContactEntity salesContact = SalesContactEntity.builder().accountId(101010L).build();
        salesContact.addContactEmail(contactEmail1);
        return salesContactRepository.save(salesContact);
    }

//    public SalesContact insertContactAndCustomField() {
//        ContactCustomField contactCustomField = ContactCustomField.builder().contactFormId(72348734L).build();
//        SalesContact salesContact = SalesContact.builder().accountId(10101010L).build();
//        salesContact.setContactCustomField(contactCustomField);
//        return salesContactRepository.save(salesContact);
//    }

    public void deleteSalesContact() {
        SalesContactEntity salesContact = salesContactRepository.findById(266975L).get();
        salesContactRepository.delete(salesContact);
    }
}
