package dk.kea.quickbook.repository;

import dk.kea.quickbook.dto.ReservationResponse;
import dk.kea.quickbook.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    boolean existsByRoom_IdAndReservationDate(int roomId, LocalDate reservationDate);
    //existByRoom_IdAndReservationDate is a method that checks if a reservation exists by room id and reservation date
    List<Reservation> findAllByGuest_Username(String username);
    //findAllByGuest_Username is a method that finds all reservations by guest username
}
