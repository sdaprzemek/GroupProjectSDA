package pl.sda.commons.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientID;

    @NotNull
    @Size(min = 3)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 3)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Min(9)
    @Column(name = "phone")
    private Long phone;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private transient List<Car> cars;

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", name='" + name + '\'' +
                ", last_name='" + lastName + '\'' +
                ", phone=" + phone +
                '}';
    }
}
