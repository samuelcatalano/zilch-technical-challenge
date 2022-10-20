package co.uk.code.challenge.samuel.catalano.zilch.service;

import co.uk.code.challenge.samuel.catalano.zilch.exception.AccountNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.Account;
import co.uk.code.challenge.samuel.catalano.zilch.repository.AccountRepository;
import co.uk.code.challenge.samuel.catalano.zilch.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Service
public class AccountService implements BaseService<Account> {

    @Autowired
    private AccountRepository repository;

    /**
     * Returns a account by id.
     * @param id the user id
     * @return User
     */
    public Account findById(final Long id) {
        return this.repository.findById(id).get();
    }

    /**
     * Save or update an account.
     * @param account an account to be saved
     * @return Account
     */
    public Account saveOrUpdate(final Account account) {
        return this.repository.save(account);
    }

    /**
     * Returns account by user document number
     * @param userDocumentNumber the document number of the user account
     * @return Account
     * @throws Exception
     */
    public Account findByUserDocumentNumber(final String userDocumentNumber) throws AccountNotFoundException {
        return this.repository.findByUserDocumentNumber(userDocumentNumber);
    }

    /**
     * Return all acounts.
     * @return List<Account> accounts
     */
    public List<Account> findAll() {
        return (List<Account>) this.repository.findAll();
    }

    /**
     * Returns account by sort code.
     * @param sortCode the account sort code
     * @return Account
     * @throws Exception
     */
    public Account findBySortCode(final String sortCode) throws AccountNotFoundException {
        return this.repository.findBySortCode(sortCode);
    }

    /**
     * Returns account by account number.
     * @param accountNumber the account number
     * @return Account
     * @throws Exception
     */
    public Account findByAccountNumber(final Long accountNumber) throws AccountNotFoundException {
        return this.repository.findByAccountNumber(accountNumber);
    }

    /**
     * Returns account by card number.
     * @param cardNumber the account card number
     * @return Account
     * @throws Exception
     */
    public Account findByCardNumber(final Long cardNumber) throws AccountNotFoundException {
        return this.repository.findByCardNumber(cardNumber);
    }

    /**
     * Returns the account balance by account number
     * @param accountNumber the account number
     * @return account ballance
     * @throws Exception
     */
    public Double getAccountBalance(final Long accountNumber) throws AccountNotFoundException {
        final Account account = this.repository.findByAccountNumber(accountNumber);
        return account.getBalance();
    }
}