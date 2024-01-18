package dk.kea.quickbook.api;

import dk.kea.quickbook.dto.HotelRequest;
import dk.kea.quickbook.dto.HotelResponse;
import dk.kea.quickbook.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    //Admin
    @PostMapping()
    public HotelResponse createHotel(@RequestBody HotelRequest hotelRequest){
        return hotelService.createHotel(hotelRequest);
    }
    //Anonymous
    @GetMapping()
    public Page<HotelResponse> getAllHotels(Pageable pageable){ // @RequestParam String filter)
        //if(filter.equals("filter")) return hotelService.getAllHotelsFilter(pageable, filter);
        return hotelService.getAllHotels(pageable); // Add if statement and Add option
    }
    //Admin
    @GetMapping("/admin-hotels")
    public List<HotelResponse> getAllHotelsAdmin(){
        return hotelService.getAllHotelsAdmin();
    }
    //Anonymous
    @GetMapping("/{id}")
    public HotelResponse getHotel(@PathVariable int id){
        return hotelService.getHotel(id);
    }
    //Admin
    @PatchMapping()
    public HotelResponse updateHotel(@RequestBody HotelRequest hotelRequest){
        return hotelService.updateHotel(hotelRequest);
    }
    //Admin
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable int id){
        return hotelService.deleteHotel(id);
    }
}
