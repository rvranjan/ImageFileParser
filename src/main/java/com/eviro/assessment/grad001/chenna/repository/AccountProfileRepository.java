package com.eviro.assessment.grad001.chenna.repository;

import com.eviro.assessment.grad001.chenna.entity.AccountProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountProfileRepository extends JpaRepository<AccountProfile,Long> {

    Optional<AccountProfile> findByAccountHolderNameAndAccountHolderSurname
            (String accountHolderName, String accountHolderSurname);
}
