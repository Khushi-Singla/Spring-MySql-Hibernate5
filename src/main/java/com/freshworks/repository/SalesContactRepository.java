package com.freshworks.repository;

import java.util.List;
import java.util.Optional;

import com.freshworks.entities.SalesContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesContactRepository
        extends JpaRepository<SalesContactEntity, Long> {
    Optional<SalesContactEntity> findByIdAndAccountId(Long id, Long account);

    List<SalesContactEntity> findAllByAccountId(Long account);
}
