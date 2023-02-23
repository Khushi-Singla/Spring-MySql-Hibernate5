package com.freshworks.controller;

import java.time.Instant;
import java.util.Random;

import com.freshworks.entities.ContactEmail;
import com.freshworks.entities.SalesContact;
import com.freshworks.repository.ContactEmailRepository;
import com.freshworks.repository.SalesContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return salesContactRepository.findAllByAccountId(1677068770L);
    }

    @GetMapping(path="/{id}/get")
    public @ResponseBody SalesContact getContact(@PathVariable Long id) {
        return salesContactRepository.findByIdAndAccountId(id, 1677068770L).get();
    }

    @GetMapping(path="/{id}/delete")
    public @ResponseBody void deleteContact(@PathVariable Long id) {
        salesContactRepository.deleteById(id);
    }

    @GetMapping(path="/create")
    public @ResponseBody SalesContact create() {
        Random rand = new Random();
        int i = rand.nextInt();
        SalesContact contact = SalesContact.builder().firstName("First"+i)
            .accountId(1677068770L).build();
        ContactEmail contactEmail = ContactEmail.builder().email(i+"@xyz.com")
            .createdAt(Instant.now()).updatedAt(Instant.now()).contact(contact).build();
        contact.addEmail(contactEmail);
        return salesContactRepository.save(contact);
    }

    @GetMapping(path="/{id}/add-email")
    public @ResponseBody void addEmail(@PathVariable Long id) {
        SalesContact contact = salesContactRepository.findByIdAndAccountId(id, 1677068770L).get();
        ContactEmail contactEmail = ContactEmail.builder().email("1@xyz.com")
            .createdAt(Instant.now()).updatedAt(Instant.now()).contact(contact).build();
        contact.addEmail(contactEmail);
        salesContactRepository.save(contact);
    }

    @GetMapping(path="/{id}/remove-email")
    public @ResponseBody void removeEmail(@PathVariable Long id) {
        SalesContact contact = salesContactRepository.findByIdAndAccountId(id, 1677068770L).get();
        ContactEmail contactEmail = ContactEmail.builder().email("1@xyz.com")
            .createdAt(Instant.now()).contact(contact).updatedAt(Instant.now()).build();
        contact.removeEmail(contactEmail);
        salesContactRepository.save(contact);
    }

    @GetMapping(path="/{id}/sync-email")
    public @ResponseBody void syncEmail(@PathVariable Long id) {
        SalesContact contact = salesContactRepository.findByIdAndAccountId(id, 1677068770L).get();
        ContactEmail contactEmail = ContactEmail.builder().email("1@xyz.com")
            .createdAt(Instant.now()).contact(contact).updatedAt(Instant.now()).build();
        ContactEmail removed = ContactEmail.builder().email("-1094370930@xyz.com")
            .createdAt(Instant.now()).contact(contact).updatedAt(Instant.now()).build();
        contact.removeEmail(removed);
        contact.addEmail(contactEmail);
        salesContactRepository.save(contact);
    }
}
