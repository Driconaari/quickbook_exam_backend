package dk.kea.quickbook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.quickbook.entity.Room;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomResponse {
    private int id;
    private int roomNumber;
    private int numberOfBeds;
    private double price;

    public RoomResponse(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.numberOfBeds = room.getNumberOfBeds();
        this.price = room.getPrice();
    }
}
