package co.uk.code.challenge.samuel.catalano.zilch.model;

import co.uk.code.challenge.samuel.catalano.zilch.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotNull
    private String lastName;

    @Column(name = "document_number", nullable = false)
    @NotNull
    private String documentNumber;

    @JsonManagedReference
    @OneToMany(mappedBy="user")
    private List<Account> accounts;

    @JsonManagedReference
    @OneToMany(mappedBy="user")
    private List<Transfer> transfers;

}