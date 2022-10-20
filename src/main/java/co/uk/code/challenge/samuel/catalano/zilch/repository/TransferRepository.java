package co.uk.code.challenge.samuel.catalano.zilch.repository;

import co.uk.code.challenge.samuel.catalano.zilch.exception.UserNotFoundException;
import co.uk.code.challenge.samuel.catalano.zilch.model.Transfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Long> {

    @Query("SELECT t FROM Transfer t WHERE t.user.id = :userId")
    List<Transfer> findAllByUserId(final Long userId) throws UserNotFoundException;
}