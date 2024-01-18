package dk.kea.quickbook.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import dk.kea.quickbook.dto.RoomRequest;
import dk.kea.quickbook.dto.RoomResponse;
import dk.kea.quickbook.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    //Admin
    @PostMapping()
    public RoomResponse createRoom(@RequestBody RoomRequest roomRequest){
        return roomService.createRoom(roomRequest);
    }
    //Anonymous
    @GetMapping("/{hotelId}")
    public List<RoomResponse> getAllRooms(@PathVariable int hotelId){
        return roomService.getAllRooms(hotelId);
    }
//    //Guest
//    @GetMapping("/free-rooms/{hotelId}")
//    public List<RoomResponse> getFreeRooms(@PathVariable int hotelId, @RequestParam @JsonFormat(pattern = "yyyy-MM-dd") LocalDate date){
//        return roomService.getFreeRooms(hotelId, date);
//    }
}
