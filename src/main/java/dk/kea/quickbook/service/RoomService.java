package dk.kea.quickbook.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import dk.kea.quickbook.dto.RoomRequest;
import dk.kea.quickbook.dto.RoomResponse;
import dk.kea.quickbook.entity.Hotel;
import dk.kea.quickbook.entity.Room;
import dk.kea.quickbook.repository.HotelRepository;
import dk.kea.quickbook.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomService {
    RoomRepository roomRepository;
    HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }
    public RoomResponse createRoom(RoomRequest roomRequest) {
        Hotel hotel = hotelRepository.findById(roomRequest.getHotelId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));
        int roomNumber = roomRequest.getRoomNumber();
        if(roomRepository.existsByRoomNumberAndHotel_Id(roomNumber, hotel.getId())){ //JpA query to make sure room doesn't exist
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room number already exists");
        }
        Room room = new Room(roomNumber, roomRequest.getNumberOfBeds(), roomRequest.getPrice(), hotel);
        roomRepository.save(room);
        return new RoomResponse(room);
    }
    public List<RoomResponse> getAllRooms(int hotelId) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
        }
        List<Room> rooms = roomRepository.findAllByHotelId(hotelId);
        return rooms.stream().map(res -> new RoomResponse(res)).toList();
    }
//    public List<RoomResponse> getFreeRooms(int hotelId, LocalDate date) {
//        if(!hotelRepository.existsById(hotelId)){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
//        }
//        List<Room> rooms = roomRepository.findRoomsByHotelIdAndReservationsNotContaining(hotelId, date);
//        return rooms.stream().map(res -> new RoomResponse(res)).toList();
//    }
}
