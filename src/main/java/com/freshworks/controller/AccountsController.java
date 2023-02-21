package com.freshworks.controller;

import com.freshworks.entities.AccountTagAssociation;
import com.freshworks.entities.SalesAccount;
import com.freshworks.repository.AccountTagAssociationRepository;
import com.freshworks.repository.SalesAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/accounts")
public class AccountsController {
    @Autowired
    private SalesAccountRepository salesAccountRepository;
    @Autowired
    private AccountTagAssociationRepository accountTagAssociationRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<SalesAccount> getAll() {
        return salesAccountRepository.findAllByAccountId(1653575335L);
    }

    @GetMapping(path="/{id}/get")
    public @ResponseBody SalesAccount getAccount(@PathVariable Long id) {
        return salesAccountRepository.findByIdAndAccountId(id, 1653575335L).get();
    }

    @GetMapping(path="/{id}/update")
    public @ResponseBody SalesAccount updateAccount(@PathVariable Long id) {
        SalesAccount account = salesAccountRepository.findByIdAndAccountId(id, 1653575335L).get();
        AccountTagAssociation tag = AccountTagAssociation.builder().tagId(9L).accountTagAssociations(account).build();
        accountTagAssociationRepository.save(tag);
        return salesAccountRepository.findByIdAndAccountId(id, 1653575335L).get();
    }
}
