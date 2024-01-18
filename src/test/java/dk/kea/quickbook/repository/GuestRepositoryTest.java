package dk.kea.quickbook.repository;

import dk.kea.quickbook.entity.Guest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestRepositoryTest {

    @Mock
    GuestRepository guestRepository; //Test user isn't used - i thought at first i needed him forgot to delete
    Guest testUser(){
        return new Guest("un", "em", "pw", "fn", "ln", "45454545");
    }
    @Test
    void existsByEmail() {
        when(guestRepository.existsByEmail("em")).thenReturn(true);
        boolean res = guestRepository.existsByEmail("em");
        assertTrue(res);
    }
    @Test
    void existsByEmailFail() {
        when(guestRepository.existsByEmail("em")).thenReturn(false);
        boolean res = guestRepository.existsByEmail("em");
        assertFalse(res);
    }
}