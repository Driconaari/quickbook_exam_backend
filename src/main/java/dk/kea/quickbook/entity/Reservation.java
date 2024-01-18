package dk.kea.quickbook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation extends DateTimeInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;
    private double price;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Reservation(LocalDate reservationDate, Guest guest, Room room){
        this.reservationDate = reservationDate;
        this.guest = guest;
        this.room = room;
        this.price = room.getPrice();
        guest.addReservation(this);
        room.addReservation(this);
    }
}
