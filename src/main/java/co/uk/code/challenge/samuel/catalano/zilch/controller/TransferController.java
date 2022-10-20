package co.uk.code.challenge.samuel.catalano.zilch.controller;

import co.uk.code.challenge.samuel.catalano.zilch.exception.AccountNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.exception.InsufficientBalanceException;
import co.uk.code.challenge.samuel.catalano.zilch.exception.UserNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.json.TransferRequestJSON;
import co.uk.code.challenge.samuel.catalano.zilch.model.Transfer;
import co.uk.code.challenge.samuel.catalano.zilch.service.TransferService;
import co.uk.code.challenge.samuel.catalano.zilch.view.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@RestController
@RequestMapping(value = "zilch/transfer")
public class TransferController {

    @Autowired
    private TransferService service;

    @PostMapping(value = "transaction")
    public ResponseEntity<?> makeTransferBetweenAccounts(@RequestBody final TransferRequestJSON request) {
        final Transfer transfer;
        try {
            transfer = this.service.makeTransferBetweenAccounts(request.getOrigin(), request.getDestiny(), request.getValue());
            return ResponseEntity.ok(transfer);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage()
                            .code(500)
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }
    }

    @GetMapping(value = "get-all-by-user/{userId}")
    public ResponseEntity<?> getAllTransferByUser(@PathVariable(value = "userId") final Long userId) {
        try {
            final List<Transfer> transfers = this.service.findAllByUser(userId);
            return ResponseEntity.ok(transfers);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage()
                            .code(500)
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }
    }
}