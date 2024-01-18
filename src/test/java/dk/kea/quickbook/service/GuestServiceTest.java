package dk.kea.quickbook.service;

import dk.kea.quickbook.dto.GuestRequest;
import dk.kea.quickbook.dto.GuestResponse;
import dk.kea.quickbook.entity.Guest;
import dk.kea.quickbook.repository.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    GuestService guestService;

    @Mock
    GuestRepository guestRepository;

    @BeforeEach
    void setUp(){
        guestService = new GuestService(guestRepository);
    }

    @Test
    void testCreateGuest() {
        Guest guest = new Guest("un", "pw", "em", "fn", "ln", "45454545");
        when(guestRepository.save(any(Guest.class))).thenReturn(guest);
        GuestRequest req = new GuestRequest("un", "pw", "em", "fn", "ln", "45454545");
        GuestResponse res = guestService.createGuest(req);
        assertEquals("un", res.getUsername());
    }
    @Test
    void testCreateGuestFailUsername(){
        when(guestRepository.existsById("un")).thenReturn(true);
        GuestRequest req = new GuestRequest("un", "pw", "em", "fn", "ln", "45454545");
        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> guestService.createGuest(req));
        assertEquals("Guest already exists", response.getReason());
    }
    @Test
    void testCreateGuestFailEmail(){
        when(guestRepository.existsByEmail("em")).thenReturn(true);
        GuestRequest req = new GuestRequest("un", "pw", "em", "fn", "ln","45454545");
        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> guestService.createGuest(req));
        assertEquals("Email already exists", response.getReason());
    }
}