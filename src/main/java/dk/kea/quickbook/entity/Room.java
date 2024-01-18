package dk.kea.quickbook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Room extends DateTimeInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int roomNumber;
    @Column(nullable = false)
    private int numberOfBeds;
    @Column(nullable = false)
    private double price;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
    public Room(int roomNumber, int numberOfBeds, double price, Hotel hotel){
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.hotel = hotel;
        hotel.addRoom(this);
    }
}
