package co.uk.code.challenge.samuel.catalano.zilch.service;

import co.uk.code.challenge.samuel.catalano.zilch.exception.AccountNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.exception.InsufficientBalanceException;
import co.uk.code.challenge.samuel.catalano.zilch.exception.UserNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.Account;
import co.uk.code.challenge.samuel.catalano.zilch.model.Transfer;
import co.uk.code.challenge.samuel.catalano.zilch.repository.TransferRepository;
import co.uk.code.challenge.samuel.catalano.zilch.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Service
public class TransferService implements BaseService<Transfer> {

    @Autowired
    private TransferRepository repository;

    @Autowired
    private AccountService accountService;

    /**
     * Returns all transfers by a specific user
     * @param userId the user id
     * @return List<Transfer>
     */
    public List<Transfer> findAllByUser(final Long userId) throws UserNotFoundException {
        try {
            return this.repository.findAllByUserId(userId);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("There is no user with this ID!", e);
        }
    }

    /**
     * Returns a transfer between two accounts
     * @param accountNumberOrigin number of origin account
     * @param accountNumberDestiny number of destiny account
     * @param value the value of transaction
     * @return Transfer
     * @throws AccountNotFoundException
     * @throws InsufficientBalanceException
     */
    public Transfer makeTransferBetweenAccounts(final Long accountNumberOrigin, final Long accountNumberDestiny, final Double value)
            throws AccountNotFoundException, InsufficientBalanceException {
        final Account origin;
        final Account destiny;
        Transfer transfer;

        try {
            origin = accountService.findByAccountNumber(accountNumberOrigin);
            destiny = accountService.findByAccountNumber(accountNumberDestiny);

            if (origin.getBalance() < value) {
                throw new InsufficientBalanceException("Insufficient balance for transfer!");
            }

            destiny.setBalance(destiny.getBalance() + value);
            origin.setBalance(origin.getBalance() - value);

            accountService.saveOrUpdate(destiny);
            accountService.saveOrUpdate(origin);

            transfer = new Transfer();
            transfer.setValue(value);
            transfer.setDate(new Date());
            transfer.setUser(origin.getUser());
            transfer.setOrigin(origin.getAccountNumber());
            transfer.setDestiny(destiny.getAccountNumber());

            transfer = this.repository.save(transfer);

        } catch (AccountNotFoundException e) {
            throw new AccountNotFoundException("Account was not founded!", e);
        }

        return transfer;
    }

    @Override
    public Transfer findById(final Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Transfer> findAll() {
        return (List<Transfer>) this.repository.findAll();
    }
}