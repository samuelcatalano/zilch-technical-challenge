package co.uk.code.challenge.samuel.catalano.zilch.controller;

import co.uk.code.challenge.samuel.catalano.zilch.exception.UserNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.User;
import co.uk.code.challenge.samuel.catalano.zilch.service.UserService;
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
@RequestMapping(value = "zilch/user")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * Create a new user.
     * @param user the new user
     * @return user created
     */
    @PostMapping(value = "create")
    public ResponseEntity<User> createNewUser(@RequestBody final User user) {
        final User created = this.service.createNewUser(user);
        return ResponseEntity.ok(created);
    }

    /**
     * Returns an user by id.
     * @param id the user id
     * @return User
     */
    @GetMapping(value = "get-by-id/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") final Long id) {
        final User user = this.service.findById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Returns an user by name.
     * @param name the user name
     * @return User
     */
    @GetMapping(value = "get-by-name/{name}")
    public ResponseEntity<?> findByName(@PathVariable(value = "name") final String name) {
        final User user;
        try {
            user = this.service.findByName(name);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage()
                            .code(500)
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }
    }

    /**
     * Returns an user by name.
     * @param documentNumber the user document number
     * @return User
     */
    @GetMapping(value = "get-by-document/{documentNumber}")
    public ResponseEntity<?> findByDocumentNumber(@PathVariable(value = "documentNumber") final String documentNumber) {
        final User user;
        try {
            user = this.service.findByDocumentNumber(documentNumber);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
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
    public ResponseEntity<List<User>> findAll() {
        final List<User> users = this.service.findAll();
        return ResponseEntity.ok(users);
    }
}