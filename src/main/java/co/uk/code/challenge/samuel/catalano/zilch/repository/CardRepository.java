package co.uk.code.challenge.samuel.catalano.zilch.repository;

import co.uk.code.challenge.samuel.catalano.zilch.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {}