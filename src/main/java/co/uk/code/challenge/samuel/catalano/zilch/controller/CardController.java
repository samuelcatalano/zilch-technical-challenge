package co.uk.code.challenge.samuel.catalano.zilch.controller;

import co.uk.code.challenge.samuel.catalano.zilch.exception.AccountNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.Card;
import co.uk.code.challenge.samuel.catalano.zilch.service.CardService;
import co.uk.code.challenge.samuel.catalano.zilch.view.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@RestController
@RequestMapping(value = "zilch/card")
public class CardController {

    @Autowired
    private CardService service;

    /**
     * Returns a new card.
     * @param accountNumber the account number to set the new card
     * @return Card
     */
    @PostMapping(value = "new-card/{accountNumber}")
    public ResponseEntity<?> getNewCard(@PathVariable(value = "accountNumber") final Long accountNumber) {
        try {
            final Card card = this.service.generateNewCard(accountNumber);
            return ResponseEntity.ok(card);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage()
                            .code(500)
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }

    }
}