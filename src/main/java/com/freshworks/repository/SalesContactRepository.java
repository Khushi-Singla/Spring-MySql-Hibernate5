package com.freshworks.repository;

import java.util.List;
import java.util.Optional;

import com.freshworks.entities.SalesContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesContactRepository
        extends JpaRepository<SalesContact, Long> {
    Optional<SalesContact> findByIdAndAccountId(Long id, Long account);

    List<SalesContact> findAllByAccountId(Long account);
    List<SalesContact> findAllByAccountIdAndMcrIdIn(Long accountId, List<Long> mcrIds);
}
