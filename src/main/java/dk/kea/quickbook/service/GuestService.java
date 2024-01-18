package dk.kea.quickbook.service;

import dk.kea.quickbook.dto.GuestRequest;
import dk.kea.quickbook.dto.GuestResponse;
import dk.kea.quickbook.entity.Guest;
import dk.kea.quickbook.repository.GuestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static dk.kea.security.entity.Role.GUEST;

@Service
public class GuestService {
    GuestRepository guestRepository;
    //A convoluted method of this exist in the userWithRolesService but it doesn't work with phone numbers and names
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public GuestResponse createGuest(GuestRequest guestRequest) {
        Guest guest = GuestRequest.getGuestEntity(guestRequest);
        if(guestRepository.existsById(guest.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Guest already exists");
        }
        if(guestRepository.existsByEmail(guest.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        guest.addRole(GUEST);
        guestRepository.save(guest);
        return new GuestResponse(guest);
    }
}
