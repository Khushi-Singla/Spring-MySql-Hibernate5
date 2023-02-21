package com.freshworks.repository;

import com.freshworks.entities.AccountTagAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTagAssociationRepository
        extends JpaRepository<AccountTagAssociation, Long> {
}
