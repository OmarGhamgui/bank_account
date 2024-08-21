package org.challenge.bank.infrastructure.persistance.postgres.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsAccountEntityById(Long id);
}
