package dk.kea.quickbook.service;

import dk.kea.quickbook.dto.ReservationRequest;
import dk.kea.quickbook.dto.ReservationResponse;
import dk.kea.quickbook.entity.Guest;
import dk.kea.quickbook.entity.Hotel;
import dk.kea.quickbook.entity.Reservation;
import dk.kea.quickbook.entity.Room;
import dk.kea.quickbook.repository.GuestRepository;
import dk.kea.quickbook.repository.HotelRepository;
import dk.kea.quickbook.repository.ReservationRepository;
import dk.kea.quickbook.repository.RoomRepository;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@DataJpaTest - can use H2 database if you want but i mock repos.
@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    ReservationService reservationService;
    @Mock
    ReservationRepository reservationRepository;
    @Mock
    HotelRepository hotelRepository;
    @Mock
    RoomRepository roomRepository;
    @Mock
    GuestRepository guestRepository;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(reservationRepository, roomRepository, guestRepository, hotelRepository);
        //Guest guest = new Guest("un", "pw", "em", "fn", "ln", "45454545");
        //repository.save(guest);
        //Hotel hotel = new Hotel("hotel", "hotel", "hotel", 44444, "hotel");
        //hotelRepository.save(hotel);
        //Room room = new Room(1, 1, 1,  hotel);
        //roomRepository.save(room);
        //Reservation reservation = new Reservation(LocalDate.now(), guest, room);
        //reservationRepository.save(reservation);
    }
    @Test
    void createReservation() {
        Guest guest = new Guest("un", "pw", "em", "fn", "ln", "45454545");
        Hotel hotel = new Hotel("hotel", "hotel", "hotel", 44444, "hotel");
        Room room = new Room(1, 1, 1,  hotel);
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(guestRepository.findById("un")).thenReturn(Optional.of(guest));
        when(reservationRepository.existsByRoom_IdAndReservationDate(room.getId(), LocalDate.now())).thenReturn(false);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(new Reservation(LocalDate.now(), guest, room));
        ReservationRequest req = ReservationRequest.builder().id(1).reservationDate(LocalDate.now()).roomId(1).build();
        ReservationResponse res = reservationService.createReservation(req, "un");
        assertEquals(LocalDate.now(), res.getReservationDate());
    }
    @Test
    void createReservationFail(){
        when(roomRepository.findById(1)).thenReturn(Optional.empty());
        ReservationRequest req = ReservationRequest.builder().id(1).reservationDate(LocalDate.now()).roomId(1).build();
        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> reservationService.createReservation(req, "un"));
        assertEquals("Room not found", response.getReason());
    }
    @Test
    void createReservationFail2(){
        Hotel hotel = new Hotel("hotel", "hotel", "hotel", 44444, "hotel");
        Room room = new Room(1, 1, 1,  hotel);
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(guestRepository.findById("un")).thenReturn(Optional.empty());
        ReservationRequest req = ReservationRequest.builder().reservationDate(LocalDate.now()).roomId(1).build();
        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> reservationService.createReservation(req, "un"));
        assertEquals("Guest not found", response.getReason());
    }
    @Test
    void createReservationFail3(){
        Guest guest = new Guest("un", "pw", "em", "fn", "ln", "45454545");
        Hotel hotel = new Hotel("hotel", "hotel", "hotel", 44444, "hotel");
        Room room = new Room(1, 1, 1,  hotel);
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(guestRepository.findById("un")).thenReturn(Optional.of(guest));
        when(reservationRepository.existsByRoom_IdAndReservationDate(room.getId(), LocalDate.now())).thenReturn(true);
        ReservationRequest req = ReservationRequest.builder().reservationDate(LocalDate.now()).roomId(1).build();
        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> reservationService.createReservation(req, "un"));
        assertEquals("Room is already reserved", response.getReason());
    }

    @Test
    void deleteReservation() {
        LocalDate date = LocalDate.now();
        Guest guest = new Guest("un", "pw", "em", "fn", "ln", "45454545");
        Hotel hotel = new Hotel("hotel", "hotel", "hotel", 44444, "hotel");
        Room room = new Room(1, 1, 1,  hotel);
        when(reservationRepository.findById(1)).thenReturn(Optional.of(new Reservation(LocalDate.now(), guest, room)));
        ReservationRequest req = ReservationRequest.builder().id(1).reservationDate(date).roomId(1).build();
        String res = reservationService.deleteReservation(req, "un");
        assertEquals("{\"message\":\"Reservation deleted\"}", res);
    }
    @Test
    void deleteReservationFail(){
        when(reservationRepository.findById(1)).thenReturn(Optional.empty());
        ReservationRequest req = ReservationRequest.builder().id(1).reservationDate(LocalDate.now()).roomId(1).build();
        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> reservationService.deleteReservation(req, "un"));
        assertEquals("Reservation not found", response.getReason());
    }
    @Test
    void deleteReserationFail2(){
        LocalDate date = LocalDate.now();
        Guest guest = new Guest("un", "pw", "em", "fn", "ln", "45454545");
        Hotel hotel = new Hotel("hotel", "hotel", "hotel", 44444, "hotel");
        Room room = new Room(1, 1, 1,  hotel);
        when(reservationRepository.findById(1)).thenReturn(Optional.of(new Reservation(LocalDate.now(), guest, room)));
        ReservationRequest req = ReservationRequest.builder().id(1).reservationDate(date).roomId(1).build();
        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> reservationService.deleteReservation(req, "un2"));
        assertEquals("You are not allowed to delete this reservation", response.getReason());
    }

    @Test
    void getAllReservationsForGuest() {
        Guest guest = new Guest("un", "pw", "em", "fn", "ln", "45454545");
        Hotel hotel = new Hotel("hotel", "hotel", "hotel", 44444, "hotel");
        Room room = new Room(1, 1, 1,  hotel);
        when(guestRepository.findById("un")).thenReturn(Optional.of(guest));
        when(reservationRepository.findAllByGuest_Username("un")).thenReturn(List.of(new Reservation(LocalDate.now(), guest, room)));
        List<ReservationResponse> reservations = reservationService.getAllReservationsForGuest("un");
        assertEquals(1, reservations.size());
    }
    @Test
    void getAllReservationsForGuestFail(){
        when(guestRepository.findById("un")).thenReturn(Optional.empty());
        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> reservationService.getAllReservationsForGuest("un"));
        assertEquals("Guest not found", response.getReason());
    }
}