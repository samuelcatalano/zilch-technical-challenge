package co.uk.code.challenge.samuel.catalano.zilch.json;

import lombok.Data;
import java.io.Serializable;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Data
public class TransferRequestJSON implements Serializable {

    private Long origin;
    private Long destiny;
    private Double value;
}