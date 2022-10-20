package co.uk.code.challenge.samuel.catalano.zilch.service;

import co.uk.code.challenge.samuel.catalano.zilch.exception.UserNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.User;
import co.uk.code.challenge.samuel.catalano.zilch.repository.UserRepository;
import co.uk.code.challenge.samuel.catalano.zilch.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Service
public class UserService implements BaseService<User> {

    @Autowired
    private UserRepository repository;

    /**
     * Create new user.
     * @param user a new user
     * @return user created
     */
    public User createNewUser(final User user) {
        return this.repository.save(user);
    }

    /**
     * Returns an user by id.
     * @param id the user id
     * @return User
     */
    public User findById(final Long id) {
        return this.repository.findById(id).get();
    }

    /**
     * Returns an user by first name and last name.
     * @param name the user name
     * @return User
     * @throws Exception
     */
    public User findByName(final String name) throws UserNotFoundException {
        String[] names = name.split(" ");
        if (names.length > 1) {
            return this.repository.findByFirstNameAndAndLastName(names[0], names[1]);
        } else {
            throw new UserNotFoundException("You must search by name and surrname!");
        }
    }

    /**
     * Returns an user by first name and last name.
     * @param documentNumber the user document number
     * @return User
     * @throws Exception
     */
    public User findByDocumentNumber(final String documentNumber) throws UserNotFoundException {
        return this.repository.findByDocumentNumber(documentNumber);
    }

    /**
     * Returns all users in database.
     * @return List<User> users
     * @throws Exception
     */
    public List<User> findAll() {
        return (List<User>) this.repository.findAll();
    }
}