package com.freshworks.controller;

import java.time.Instant;

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
        return salesContactRepository.findAllByAccountId(1653575335L);
    }

    @GetMapping(path="/{id}/get")
    public @ResponseBody SalesContact getContact(@PathVariable Long id) {
        return salesContactRepository.findByIdAndAccountId(id, 1653575335L).get();
    }

    @GetMapping(path="/{id}/update")
    public @ResponseBody SalesContact updateContact(@PathVariable Long id) {
        //Contact
        //Contact Custom Field
        //Contact Custom Field2
        //Contact Email
        //Contact Phone number
        SalesContact contact = salesContactRepository.findByIdAndAccountId(id, 1653575335L).get();
        ContactEmail contactEmail = ContactEmail.builder().email("1@xyz.com")
            .createdAt(Instant.now()).updatedAt(Instant.now()).contact(contact).build();
        ContactEmail saved = contactEmailRepository.save(contactEmail);
        return salesContactRepository.findByIdAndAccountId(id, 1653575335L).get();
    }
}
