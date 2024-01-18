package dk.kea.quickbook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.quickbook.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomRequest {
    private int roomNumber;
    private int numberOfBeds;
    private double price;
    private int hotelId;

    public RoomRequest(int roomNumber, int numberOfBeds, double price, int hotelId) { //Why not use builder
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.hotelId = hotelId;
    }
}
