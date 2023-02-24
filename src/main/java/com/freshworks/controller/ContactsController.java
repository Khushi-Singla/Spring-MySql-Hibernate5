package com.freshworks.controller;

import java.time.Instant;

import com.freshworks.entities.ContactCustomField;
import com.freshworks.entities.ContactEmail;
import com.freshworks.entities.SalesContact;
import com.freshworks.repository.ContactEmailRepository;
import com.freshworks.repository.SalesContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/contacts")
public class ContactsController {
    @Autowired
    private SalesContactRepository salesContactRepository;

    @Autowired
    private ContactEmailRepository contactEmailRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<SalesContact> getAllContacts() {
        return salesContactRepository.findAllByAccountId(10101010L);
    }

    @GetMapping(path="/{id}/get")
    public @ResponseBody SalesContact getContact(@PathVariable Long id) {
        return salesContactRepository.findByIdAndAccountId(id, 10101010L).get();
    }

    @GetMapping(path="/{id}/update")
    public @ResponseBody SalesContact updateContact(@PathVariable Long id) {
        //Contact
        //Contact Custom Field
        //Contact Custom Field2
        //Contact Email
        //Contact Phone number
        SalesContact contact = salesContactRepository.findByIdAndAccountId(id, 10101010L).get();
        ContactEmail contactEmail = ContactEmail.builder().email("1@xyz.com")
            .createdAt(Instant.now()).updatedAt(Instant.now()).build();
//        ContactEmail saved = contactEmailRepository.save(contactEmail);
        contact.addContactEmail(contactEmail);
        return salesContactRepository.save(contact);
    }
    @PostMapping(path="/postEntity")
    public void postEntity() {
//       insertContactAndEmail();
        insertContactAndCustomField();
    }

    @DeleteMapping(path = "/delete")
    public void deleteEntity() {
        deleteSalesContact();
    }

    public SalesContact insertContactAndEmail() {
        ContactEmail contactEmail1 = ContactEmail.builder().email("xpqr@gmail.com").build();
        SalesContact salesContact = SalesContact.builder().accountId(101010L).build();
        salesContact.addContactEmail(contactEmail1);
        return salesContactRepository.save(salesContact);
    }

    public SalesContact insertContactAndCustomField() {
        ContactCustomField contactCustomField = ContactCustomField.builder().contactFormId(72348734L).build();
        SalesContact salesContact = SalesContact.builder().accountId(10101010L).build();
        salesContact.setContactCustomField(contactCustomField);
        return salesContactRepository.save(salesContact);
    }

    public void deleteSalesContact() {
        SalesContact salesContact = salesContactRepository.findById(267006L).get();
        System.out.println(salesContact.getId());
        salesContactRepository.delete(salesContact);
    }
}
