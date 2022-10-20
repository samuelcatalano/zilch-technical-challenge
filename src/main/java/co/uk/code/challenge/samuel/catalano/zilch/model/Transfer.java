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
@Table(name = "transfer")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Transfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_origin", nullable = false)
    private Long origin;

    @Column(name = "account_destiny", nullable = false)
    private Long destiny;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "dt_tranfer", nullable = false)
    private Date date;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
}