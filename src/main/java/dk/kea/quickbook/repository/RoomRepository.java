package dk.kea.quickbook.repository;

import dk.kea.quickbook.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer>{
    boolean existsByRoomNumberAndHotel_Id(int roomNumber, int hotelId);
    List<Room> findAllByHotelId(int hotelId);
//    //Select from room where hotel_id = hotelId and not exists (select from reservation where room_id = room.id and reservation_date = date)
//    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND NOT EXISTS (SELECT res FROM Reservation res WHERE res.room = r AND res.reservationDate = :date)")
//    List<Room> findRoomsByHotelIdAndReservationsNotContaining(@Param("hotelId") int hotelId, @Param("date") LocalDate date);
}
