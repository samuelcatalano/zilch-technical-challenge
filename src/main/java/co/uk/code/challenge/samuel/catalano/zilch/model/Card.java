package co.uk.code.challenge.samuel.catalano.zilch.model;

import co.uk.code.challenge.samuel.catalano.zilch.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "card")
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "expiration", nullable = false)
    private Date expirationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
}