package dk.kea.quickbook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd") //Important to format things from JSON otherwise it made error.
    private LocalDate reservationDate;
    private int roomId;
}
