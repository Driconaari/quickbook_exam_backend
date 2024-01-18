package dk.kea.quickbook.entity;

import dk.kea.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor //Needed for JPA. It retrieves the data from the database and populates a gust object
@Entity //JPA annotation
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Joined - each concrete + need join. Single - all in one table+discriminator
@DiscriminatorColumn(name = "USER_TYPE")
public class Guest extends UserWithRoles {
    @Column(nullable = false, length = 55)
    private String firstName;
    @Column(nullable = false, length = 55)
    private String lastName;
    @Column(length = 20)
    private String phoneNumber;
    @OneToMany(mappedBy = "guest") //JPA annotation
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public Guest(String username, String email, String password, String firstName, String lastName, String phoneNumber){
        super(username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
