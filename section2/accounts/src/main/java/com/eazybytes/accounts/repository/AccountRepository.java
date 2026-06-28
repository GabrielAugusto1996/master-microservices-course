package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(Long customerId);

    Optional<Account> findByAccountNumber(Long accountNumber);

    //NOTE: AuditAware only works if use the method save(), if you need to create a custom update, must be performed it manually
    @Modifying
    @Query("""
       UPDATE Account
       SET accountType = :accountType,
           branchAddress = :branchAddress,
           updatedAt = current_timestamp,
           updatedBy =  'account_microservice'
       WHERE accountNumber = :accountNumber
    """)
    Integer updateAccountDetailsByAccountNumber(String accountType, String branchAddress, Long accountNumber);
}