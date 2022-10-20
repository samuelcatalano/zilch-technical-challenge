package co.uk.code.challenge.samuel.catalano.zilch.repository;

import co.uk.code.challenge.samuel.catalano.zilch.exception.UserNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findById(final Long id);
    User findByFirstNameAndAndLastName(final String firstName, final String lastName) throws UserNotFoundException;
    User findByDocumentNumber(final String documentNumber) throws UserNotFoundException;
}