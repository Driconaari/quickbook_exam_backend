package dk.kea.quickbook.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ReservationRepositoryTest {

    @Mock
    ReservationRepository reservationRepository;

    @Test
    void existsByRoom_IdAndReservationDate() {
        when(reservationRepository.existsByRoom_IdAndReservationDate(1, LocalDate.of(2021, 5, 5))).thenReturn(true);
        boolean res = reservationRepository.existsByRoom_IdAndReservationDate(1, LocalDate.of(2021, 5, 5));
        assertTrue(res);
    }
    @Test
    void existsByRoom_IdAndReservationDateFail() {
        when(reservationRepository.existsByRoom_IdAndReservationDate(1, LocalDate.of(2021, 5, 5))).thenReturn(false);
        boolean res = reservationRepository.existsByRoom_IdAndReservationDate(1, LocalDate.of(2021, 5, 5));
        assertFalse(res);
    }
    @Test
    void findAllByGuest_Username() {
        when(reservationRepository.findAllByGuest_Username("un")).thenReturn(null);
        assertNull(reservationRepository.findAllByGuest_Username("un"));
    }
    @Test
    void findAllByGuest_UsernameFail(){
        when(reservationRepository.findAllByGuest_Username("un")).thenReturn(List.of());
        assertEquals(List.of(), reservationRepository.findAllByGuest_Username("un"));
    }
}