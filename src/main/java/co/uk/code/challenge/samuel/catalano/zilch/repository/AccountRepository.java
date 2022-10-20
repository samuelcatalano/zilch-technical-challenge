package co.uk.code.challenge.samuel.catalano.zilch.repository;

import co.uk.code.challenge.samuel.catalano.zilch.exception.AccountNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findById(final Long id);
    Account findBySortCode(final String sortCode) throws AccountNotFoundException;
    Account findByAccountNumber(final Long accountNumber) throws AccountNotFoundException;
    Account findByCardNumber(final Long cardNumber) throws AccountNotFoundException;
    Account findByUserDocumentNumber(final String userDocumentNumber) throws AccountNotFoundException;
}