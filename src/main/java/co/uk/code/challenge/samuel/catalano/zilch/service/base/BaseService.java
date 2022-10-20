package co.uk.code.challenge.samuel.catalano.zilch.service.base;

import co.uk.code.challenge.samuel.catalano.zilch.model.base.BaseEntity;
import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

public interface BaseService<E extends BaseEntity> {

    E findById(final Long id);
    List<E> findAll();
}