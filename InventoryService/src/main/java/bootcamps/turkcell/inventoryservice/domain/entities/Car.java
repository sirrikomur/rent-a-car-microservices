package bootcamps.turkcell.inventoryservice.domain.entities;

import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int modelYear;
    private String licensePlate;
    private double dailyRental;

    @Enumerated(EnumType.STRING)
    private CarState state;

    @ManyToOne
    private Model model;
}
