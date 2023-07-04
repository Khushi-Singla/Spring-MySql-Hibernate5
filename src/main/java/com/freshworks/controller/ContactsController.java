package com.freshworks.controller;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.freshworks.entities.ContactEmail;
import com.freshworks.entities.ContactListAssociation;
import com.freshworks.entities.SalesContact;
import com.freshworks.repository.ContactEmailRepository;
import com.freshworks.repository.SalesContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping(path="/{id}/update")
    public @ResponseBody SalesContact updateContact(@PathVariable Long id) {
        //Contact
        //Contact Custom Field
        //Contact Custom Field2
        //Contact Email
        //Contact Phone number
        SalesContact contact = salesContactRepository.findByIdAndAccountId(id, 10101010L).get();
        ContactEmail contactEmail = ContactEmail.builder().email("1@xyz.com")
            .createdAt(Instant.now()).updatedAt(Instant.now()).build();
        ContactEmail saved = contactEmailRepository.save(contactEmail);
//        contact.addContactEmail(contactEmail);
        return salesContactRepository.save(contact);
    }
    @PostMapping(path="/postEntity")
    public @ResponseBody SalesContact postEntity() {
       return insertContactAndEmail();
//        insertContactAndCustomField();
    }

    @DeleteMapping(path = "/delete")
    public void deleteEntity() {
        deleteSalesContact();
    }

    @GetMapping(path = "/get")
    public void getRecords() {
        Optional<SalesContact> salesContacts = salesContactRepository.findByIdAndAccountId(1563682L, 1685983243L);
        System.out.println("--------------------------------------------");
        System.out.println(salesContacts.get().getContactCustomFields());
        System.out.println(salesContacts.get().getContactCustomField2());
        System.out.println(salesContacts.get().getContactSubscription());
        System.out.println(salesContacts.get().getEmails());
        System.out.println("--------------------------------------------");
        System.out.println(salesContacts.get().getTags());
    }

    @PostMapping(path = "/test")
    public void PatternTest() {
        SalesContact salesContact = new SalesContact();
        salesContact.setWorkEmail("sdjhdsjhdfjgdfgg@wer.com");
        System.out.println("742874743");
    }

    public SalesContact insertContactAndEmail() {
        ContactEmail contactEmail1 = new ContactEmail();
        contactEmail1.setEmail("geffdbhbfdbn");
        ContactListAssociation contactListAssociation = ContactListAssociation.builder().createdAt(Instant.now()).updatedAt(Instant.now()).listId(1L).build();
        SalesContact salesContact = SalesContact.builder().accountId(100L).createdAt(Instant.now()).updatedAt(Instant.now()).status(1).workEmail("dsbddbndvvdbvb").build();
        salesContact.addContactEmail(contactEmail1);
        salesContact.addContactListAssociation(contactListAssociation);
        return salesContactRepository.save(salesContact);
    }

//    public SalesContact insertContactAndCustomField() {
//        ContactCustomField contactCustomField = ContactCustomField.builder().contactFormId(72348734L).build();
//        SalesContact salesContact = SalesContact.builder().accountId(10101010L).build();
//        salesContact.setContactCustomField(contactCustomField);
//        return salesContactRepository.save(salesContact);
//    }

    public void deleteSalesContact() {
        SalesContact salesContact = salesContactRepository.findById(266975L).get();
        salesContactRepository.delete(salesContact);
    }
}
