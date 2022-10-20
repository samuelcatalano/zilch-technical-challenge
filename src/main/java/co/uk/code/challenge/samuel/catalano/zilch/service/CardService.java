package co.uk.code.challenge.samuel.catalano.zilch.service;

import co.uk.code.challenge.samuel.catalano.zilch.exception.AccountNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.Account;
import co.uk.code.challenge.samuel.catalano.zilch.model.Card;
import co.uk.code.challenge.samuel.catalano.zilch.repository.CardRepository;
import co.uk.code.challenge.samuel.catalano.zilch.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Service
public class CardService implements BaseService<Card> {

    @Autowired
    private CardRepository repository;

    @Autowired
    private AccountService accountService;

    /**
     * Returns a new card.
     * @param accountNumber the account number
     * @return a new card
     * @throws AccountNotFoundException
     */
    public Card generateNewCard(final Long accountNumber) throws AccountNotFoundException {
        final Account account = this.accountService.findByAccountNumber(accountNumber);
        final Long generateValue = (long) (1000000000000000L + new Random().nextFloat() * 9000000000000000L);

        final Card card = new Card();
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 2);
        Date expiration = cal.getTime();

        card.setNumber(generateValue);
        card.setUser(account.getUser());
        card.setExpirationDate(expiration);

        account.setCardNumber(generateValue);
        this.accountService.saveOrUpdate(account);

        return this.repository.save(card);
    }

    @Override
    public Card findById(final Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Card> findAll() {
        return (List<Card>) repository.findAll();
    }
}
