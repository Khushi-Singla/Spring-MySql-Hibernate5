package com.freshworks.repository;

import java.util.List;
import java.util.Optional;

import com.freshworks.entities.SalesAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesAccountRepository
        extends JpaRepository<SalesAccount, Long> {
    Optional<SalesAccount> findByIdAndAccountId(Long id, Long account);

    List<SalesAccount> findAllByAccountId(Long account);
}
