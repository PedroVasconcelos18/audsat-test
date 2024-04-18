package io.pedrovasconcelosdev.audsattest.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "drivers")
public class Drivers {

    @Id
    @GeneratedValue(generator = "sq_drivers")
    @SequenceGenerator(name = "sq_drivers", sequenceName = "sq_drivers", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "document")
    private String document;

    @Column(name = "birthdate")
    private LocalDate birthdate;


}
