package co.uk.code.challenge.samuel.catalano.zilch.model;

import co.uk.code.challenge.samuel.catalano.zilch.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "account")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "sort_code", nullable = false)
    @NotNull
    private String sortCode;

    @Column(name = "account_number", nullable = false)
    @NotNull
    private Long accountNumber;

    @Column(name = "card_number", nullable = false)
    @NotNull
    private Long cardNumber;

    @Column(name = "balance", nullable = false)
    @NotNull
    private Double balance;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

}