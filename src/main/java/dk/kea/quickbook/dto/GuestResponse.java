package dk.kea.quickbook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.quickbook.entity.Guest;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestResponse {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime edited;
    public GuestResponse(Guest guest) { //Why not just use AllArgsConstructor?
        this.username = guest.getUsername();
        this.email = guest.getEmail();
        this.firstName = guest.getFirstName();
        this.lastName = guest.getLastName();
        this.phoneNumber = guest.getPhoneNumber();
        this.created = guest.getCreated();
        this.edited = guest.getEdited();
    }
}
