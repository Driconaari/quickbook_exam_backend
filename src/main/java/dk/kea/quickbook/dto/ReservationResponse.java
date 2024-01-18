package dk.kea.quickbook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.quickbook.entity.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd")

    private LocalDate reservationDate;
    private double price;
    private int roomNumber;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.reservationDate = reservation.getReservationDate();
        this.price = reservation.getPrice(); //Important to get res.price for assignment. Not dependable on room.price
        this.roomNumber = reservation.getRoom().getRoomNumber();
    }
}
