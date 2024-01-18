package dk.kea.quickbook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.quickbook.entity.Hotel;
import dk.kea.quickbook.entity.Room;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //This is never used. I use my constructor in testing instead.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponse {
    private int id;
    private String name;
    private String street;
    private String city;
    private int zip;
    private String country;
    private int numberOfRooms;

    public HotelResponse(Hotel hotel){ //Filtering out rooms and only getting sizes. Rooms not needed here.
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.street = hotel.getStreet();
        this.city = hotel.getCity();
        this.zip = hotel.getZip();
        this.country = hotel.getCountry();
        if(hotel.getRooms() != null){
            List<RoomResponse> rooms = hotel.getRooms().stream().map(room -> new RoomResponse(room)).toList();
            this.numberOfRooms = rooms.size();
        }
    }
}
