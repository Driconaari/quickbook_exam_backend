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
public class Hotel extends DateTimeInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 55)
    private String name;
    @Column(nullable = false, length = 55)
    private String street;
    @Column(nullable = false, length = 55)
    private String city;
    @Column(nullable = false, length = 10)
    private int zip;
    @Column(nullable = false, length = 55)
    private String country;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE) //CascadeType.REMOVE is needed to delete a hotel+rooms
    private List<Room> rooms = new ArrayList<>();
    public void addRoom(Room room){ //Convenience method to add a room to a hotel
        rooms.add(room);
    }
    public Hotel(String name, String street, String city, int zip, String country){
        this.name = name;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }
}
