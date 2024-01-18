package dk.kea.quickbook.service;

import dk.kea.quickbook.dto.HotelRequest;
import dk.kea.quickbook.dto.HotelResponse;
import dk.kea.quickbook.dto.RoomResponse;
import dk.kea.quickbook.entity.Hotel;
import dk.kea.quickbook.entity.Reservation;
import dk.kea.quickbook.entity.Room;
import dk.kea.quickbook.repository.HotelRepository;
import dk.kea.quickbook.repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HotelService {
    HotelRepository hotelRepository;
    ReservationRepository reservationRepository;

    public HotelService(HotelRepository hotelRepository, ReservationRepository reservationRepository) {
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
    } //Create hotel have checks in frontend and backend in entity
    public HotelResponse createHotel(HotelRequest hotelRequest) {
        Hotel hotel = HotelRequest.getHotelEntity(hotelRequest);
        hotelRepository.save(hotel);
        return new HotelResponse(hotel);
    }
    //Pageable method for getting all hotels - notice we run through a method to get the response with dto filters, note empty just returns pag info
    public Page<HotelResponse> getAllHotels(Pageable pageable) { //Send filter
        Page<Hotel> hotels = hotelRepository.findAll(pageable);
        List<HotelResponse> hotelDtos = hotels.stream().map(hotel -> toHotelResponse(hotel)).toList();
        return new PageImpl<>(hotelDtos, pageable, hotels.getTotalElements());
    }
//    public Page<HotelResponse> getAllHotelsFilter(Pageable pageable, String filter) {
//        Page<Hotel> hotels = hotelRepository.findAll(filter, pageable);
//        List<HotelResponse> hotelDtos = hotels.stream().map(hotel -> toHotelResponse(hotel)).toList();
//        return new PageImpl<>(hotelDtos, pageable, hotels.getTotalElements());
//    }
    public List<HotelResponse> getAllHotelsAdmin(){ //Method for making select options
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(hotel -> new HotelResponse(hotel)).toList();
    }
    private HotelResponse toHotelResponse(Hotel hotel){ //Speciel mapping for pagination.
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setId(hotel.getId());
        hotelResponse.setName(hotel.getName());
        hotelResponse.setStreet(hotel.getStreet());
        hotelResponse.setCity(hotel.getCity());
        hotelResponse.setZip(hotel.getZip());
        hotelResponse.setCountry(hotel.getCountry());
        if(hotel.getRooms() != null){
            List<RoomResponse> rooms = hotel.getRooms().stream().map(room -> new RoomResponse(room)).toList();
            hotelResponse.setNumberOfRooms(rooms.size());
        }
        return hotelResponse;
    }

    public HotelResponse getHotel(int id) { //You could return Optional of hotel and check after. Or you could check like this.
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));
        return new HotelResponse(hotel);
    }

    public HotelResponse updateHotel(HotelRequest hotelRequest) { //Patch method is more of an update to me. But i feel put would have been just as good.
        Hotel existingHotel = hotelRepository.findById(hotelRequest.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));
        if(hotelRequest.getId() != existingHotel.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not the same hotel id");
        }
        else{
            if(hotelRequest.getName() != null) existingHotel.setName(hotelRequest.getName());
            if(hotelRequest.getStreet() != null) existingHotel.setStreet(hotelRequest.getStreet());
            if(hotelRequest.getCity() != null) existingHotel.setCity(hotelRequest.getCity());
            if(hotelRequest.getZip() != 0) existingHotel.setZip(hotelRequest.getZip());
            if(hotelRequest.getCountry() != null) existingHotel.setCountry(hotelRequest.getCountry());
        }
        Hotel updatedHotel = hotelRepository.save(existingHotel);
        return new HotelResponse(updatedHotel);
    }

    public String deleteHotel(int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));
        List<Room> rooms = hotel.getRooms();
        for(Room roomsInHotel : rooms){
            List<Reservation> reservations = roomsInHotel.getReservations();
            if(reservations.size() > 0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel has reservations");
            }
        }
        hotelRepository.deleteById(hotel.getId());
        return "{\"message\":\"Hotel deleted\"}"; //Don't really use the string but returning nothing gives error. And now i have option.
    }
//This would have been cleaner way of checking for rooms. But i did for loop because i couldn't figure it out during exam.
//        if (hotel.getRooms().stream().anyMatch(room -> room.getReservations().size() > 0)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel has reservations");
//        }
}
