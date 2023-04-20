package com.freshworks.repository;

import java.util.List;
import java.util.Optional;

import com.freshworks.entities.FdMultitenantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FdMultitenantUserRepository
        extends JpaRepository<FdMultitenantUser, Long> {
    Optional<FdMultitenantUser> findFirstByAccountIdAndAuthenticationToken(
            Long accountId, String authenticationToken);

    Optional<FdMultitenantUser> findByIdAndAccountId(Long id, Long accountId);

    @Query("select u from FdMultitenantUser u inner join u.userProfile up where up.isForgotten = false " +
        "and u.accountId = up.accountId and u.accountId=:accountId")
    List<FdMultitenantUser> findAllActiveUsers(Long accountId);
}
