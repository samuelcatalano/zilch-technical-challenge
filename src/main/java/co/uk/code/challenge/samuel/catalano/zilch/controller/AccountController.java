package co.uk.code.challenge.samuel.catalano.zilch.controller;

import co.uk.code.challenge.samuel.catalano.zilch.exception.AccountNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.Account;
import co.uk.code.challenge.samuel.catalano.zilch.service.AccountService;
import co.uk.code.challenge.samuel.catalano.zilch.view.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@RestController
@RequestMapping(value = "zilch/account")
public class AccountController {

    @Autowired
    private AccountService service;

    /**
     * Returns an user by id.
     * @param id the user id
     * @return User
     */
    @GetMapping(value = "get-by-id/{id}")
    public ResponseEntity<Account> findById(@PathVariable(value = "id") final Long id) {
        final Account account = this.service.findById(id);
        return ResponseEntity.ok(account);
    }

    /**
     * Returns account by user document number
     * @param userDocumentNumber the document number of the user account
     * @return Account
     */
    @GetMapping(value = "get-by-user-document/{userDocumentNumber}")
    public ResponseEntity<?> findByUserDocumentNumber(@PathVariable(value = "userDocumentNumber") final String userDocumentNumber) {
        final Account account;
        try {
            account = this.service.findByUserDocumentNumber(userDocumentNumber);
            return ResponseEntity.ok(account);
        } catch (final AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage()
                            .code(500)
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }
    }

    /**
     * Returns all users in database.
     * @return List<User> users
     */
    @GetMapping(value = "get-all")
    public ResponseEntity<List<Account>> findAll() {
        final List<Account> accounts = this.service.findAll();
        return ResponseEntity.ok(accounts);
    }

    /**
     * Returns the account balance by account number
     * @param accountNumber the account number
     * @return account ballance
     */
    @GetMapping(value = "get-account-ballance/{accountNumber}")
    public ResponseEntity<?> getAccountBalance(@PathVariable(value = "accountNumber") final Long accountNumber) {
        final Account account;
        try {
            account = this.service.findByAccountNumber(accountNumber);
            return ResponseEntity.ok(account.getBalance());
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage()
                            .code(500)
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }
    }
}