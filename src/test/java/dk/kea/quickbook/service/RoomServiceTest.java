package dk.kea.quickbook.service;

import dk.kea.quickbook.dto.ReservationRequest;
import dk.kea.quickbook.dto.RoomRequest;
import dk.kea.quickbook.dto.RoomResponse;
import dk.kea.quickbook.entity.Hotel;
import dk.kea.quickbook.entity.Reservation;
import dk.kea.quickbook.entity.Room;
import dk.kea.quickbook.repository.HotelRepository;
import dk.kea.quickbook.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    RoomService roomService;

    @Mock
    RoomRepository roomRepository;
    @Mock
    HotelRepository hotelRepository;
    @BeforeEach
    void setUp() {
        roomService = new RoomService(roomRepository, hotelRepository);
    }
    Room testRoom(){
        Hotel hotel = new Hotel("TestHotel","TestStreet","TestCity",9999,"TestCountry");
        return new Room(1, 2, 100, hotel);
    }
    @Test
    void createRoom() {
        Room room = testRoom();
        when(hotelRepository.findById(1)).thenReturn(java.util.Optional.of(room.getHotel()));
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        Hotel hotel = new Hotel("TestHotel","TestStreet","TestCity",9999,"TestCountry");
        hotel.setId(1);
        RoomRequest req = new RoomRequest(1, 2, 100, 1);
        RoomResponse res = roomService.createRoom(req);
        assertEquals(1, res.getRoomNumber());
    }
    @Test
    void createRoomFail(){
        when(hotelRepository.findById(1)).thenReturn(Optional.empty());
        RoomRequest req = new RoomRequest(1, 2, 100, 1);
        ResponseStatusException res = assertThrows(ResponseStatusException.class, () -> roomService.createRoom(req));
        assertEquals("Hotel not found", res.getReason());
    }
    @Test
    void createRoomFail2(){
        Hotel hotel = new Hotel("TestHotel", "TestStreet", "TestCity", 9999, "TestCountry");
        when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));
        when(roomRepository.existsByRoomNumberAndHotel_Id(1, hotel.getId())).thenReturn(true);
        RoomRequest req = new RoomRequest(1, 2, 100, 1);
        ResponseStatusException res = assertThrows(ResponseStatusException.class, () -> roomService.createRoom(req));
        assertEquals("Room number already exists", res.getReason());
    }
    @Test
    void getAllRooms() {
        Room room = testRoom();
        when(hotelRepository.existsById(1)).thenReturn(true);
        when(roomRepository.findAllByHotelId(1)).thenReturn(List.of(room));
        assertEquals(1, roomService.getAllRooms(1).size());
    }
    @Test
    void getALlRoomsFail(){
        when(hotelRepository.existsById(1)).thenReturn(false);
        ResponseStatusException res = assertThrows(ResponseStatusException.class, () -> roomService.getAllRooms(1));
        assertEquals("Hotel not found", res.getReason());
    }
}