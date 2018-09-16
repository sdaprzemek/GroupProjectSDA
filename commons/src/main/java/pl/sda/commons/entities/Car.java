package pl.sda.commons.entities;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer carID;

    @NotNull
    @Size(min = 3, max = 10)
    @Column(name = "color")
    private String color;

    @NotNull
    @Size(min = 2)
    @Column(name = "type")
    private String type;

    @NotNull
    @Size(min = 2)
    @Column(name = "mark")
    private String mark;

    @NotNull
    @Column(name = "year")
    private Integer year;

    @Size(min = 10, max = 20)
    @Column(name = "VIN")
    private String vin;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnore
    @ManyToMany(mappedBy = "cars", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private transient List<Worker> workers;

    @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", mark='" + mark + '\'' +
                ", year=" + year +
                ", VIN='" + vin + '\'' +
                '}';
    }
}
