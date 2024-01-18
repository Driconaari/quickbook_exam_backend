package dk.kea.quickbook.service;

import dk.kea.quickbook.dto.ReservationRequest;
import dk.kea.quickbook.dto.ReservationResponse;
import dk.kea.quickbook.entity.Guest;
import dk.kea.quickbook.entity.Reservation;
import dk.kea.quickbook.entity.Room;
import dk.kea.quickbook.repository.GuestRepository;
import dk.kea.quickbook.repository.HotelRepository;
import dk.kea.quickbook.repository.ReservationRepository;
import dk.kea.quickbook.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    RoomRepository roomRepository;
    GuestRepository guestRepository;
    HotelRepository hotelRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository, GuestRepository guestRepository, HotelRepository hotelRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.hotelRepository = hotelRepository;
    }

    public ReservationResponse createReservation(ReservationRequest reservationRequest, String guestName) {
        Room room = roomRepository.findById(reservationRequest.getRoomId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
        Guest guest = guestRepository.findById(guestName).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found"));
        if(reservationRepository.existsByRoom_IdAndReservationDate(room.getId(), reservationRequest.getReservationDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is already reserved");
        } ////Check reservation date is free with Jpa query
        Reservation reservation = new Reservation(reservationRequest.getReservationDate(), guest, room);
        reservationRepository.save(reservation);
        return new ReservationResponse(reservation);
    }
    public String deleteReservation(ReservationRequest reservationRequest, String guestName) {
        Reservation reservation = reservationRepository.findById(reservationRequest.getId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
        if(!reservation.getGuest().getUsername().equals(guestName)) { //If username wrong deletion denied.
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this reservation");
        }
        reservationRepository.delete(reservation);
        return "{\"message\":\"Reservation deleted\"}"; //Not really used but good practice to return a message.
    }

    public List<ReservationResponse> getAllReservationsForGuest(String name) {
        Guest guest = guestRepository.findById(name).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found"));
        List<Reservation> reservations = reservationRepository.findAllByGuest_Username(guest.getUsername());
        return reservations.stream().map(res -> new ReservationResponse(res)).toList();
    }
}
