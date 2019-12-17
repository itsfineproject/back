package itsfine.com.back.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"carNumber"})

@Entity
@Table(name = "cars", uniqueConstraints={@UniqueConstraint(columnNames={"car_number"})})
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "car_number")
    private String carNumber;

    private String carName;
    private String carPassportNumber;
    private String comment;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "car")
    private Set<FineEntity> fines;

    public CarEntity(String carNumber, String carName, String carPassportNumber, String comment, UserEntity user) {
        this.carNumber = carNumber;
        this.carName = carName;
        this.carPassportNumber = carPassportNumber;
        this.comment = comment;
        this.user = user;
    }
}
