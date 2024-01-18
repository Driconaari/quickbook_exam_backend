package dk.kea.quickbook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.quickbook.entity.Guest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor //No reason for this to be here.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public static Guest getGuestEntity(GuestRequest guestRequest){//Never used i never return guest with edited or updated fields.
        return new Guest(guestRequest.username,
                        guestRequest.email,
                        guestRequest.password,
                        guestRequest.firstName,
                        guestRequest.lastName,
                        guestRequest.phoneNumber);
    }
}
