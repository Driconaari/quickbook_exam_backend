package dk.kea.quickbook.service;

import dk.kea.quickbook.dto.HotelRequest;
import dk.kea.quickbook.dto.HotelResponse;
import dk.kea.quickbook.entity.Hotel;
import dk.kea.quickbook.repository.HotelRepository;
import dk.kea.quickbook.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    HotelService hotelService;
    ReservationRepository reservationRepository;
    @Mock
    HotelRepository hotelRepository;

    @BeforeEach
    void setUp(){
        hotelService = new HotelService(hotelRepository, reservationRepository);
    }
    Hotel testHotel(){
        return new Hotel("TestHotel","TestStreet","TestCity",9999,"TestCountry");
    }
    @Test
    void testCreateHotel() {
        Hotel hotel = testHotel();
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);
        HotelRequest req = new HotelRequest("TestHotel","TestStreet","TestCity",9999,"TestCountry");
        HotelResponse res = hotelService.createHotel(req);
        assertEquals("TestHotel", res.getName());
        assertEquals("TestStreet", res.getStreet());
        assertEquals("TestCity", res.getCity());
        assertEquals(9999, res.getZip());
        assertEquals("TestCountry", res.getCountry());
    }
    @Test
    void testCreateHotelFail(){
        Hotel hotel = testHotel();
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);
        HotelRequest req = new HotelRequest("TestHotel","TestStreet","TestCity",9999,"TestCountry");
        HotelResponse res = hotelService.createHotel(req);
        assertNotEquals("TestHotel2", res.getName());
        assertNotEquals("TestStreet2", res.getStreet());
        assertNotEquals("TestCity2", res.getCity());
        assertNotEquals(9998, res.getZip());
        assertNotEquals("TestCountry2", res.getCountry());
    }
    @Test
    void getAllHotels() { //Made this with help of chatGPT i had problems figururing out what it returns -> its data in pageform
        Hotel hotel = testHotel();
        Pageable pageable = mock(Pageable.class);
        Page<Hotel> page = new PageImpl<>(List.of(hotel));
        when(hotelRepository.findAll(pageable)).thenReturn(page);
        Page<HotelResponse> res = hotelService.getAllHotels(pageable);
        assertEquals(1, res.getSize());
    }
    @Test
    void getAllHotelsFail(){
        Pageable pageable = mock(Pageable.class);
        Page<Hotel> page = new PageImpl<>(List.of());
        when(hotelRepository.findAll(pageable)).thenReturn(page);
        Page<HotelResponse> res = hotelService.getAllHotels(pageable);
        assertEquals(0, res.getSize());
    }
    @Test
    void getAllHotelsAdmin() {
        Hotel hotel = testHotel();
        when(hotelRepository.findAll()).thenReturn(List.of(hotel));
        List<HotelResponse> res = hotelService.getAllHotelsAdmin();
        assertEquals(1, res.size());
    }
    @Test
    void getAllHotelsAdminFail(){
        when(hotelRepository.findAll()).thenReturn(List.of());
        List<HotelResponse> res = hotelService.getAllHotelsAdmin();
        assertEquals(0, res.size());
    }

    @Test
    void getHotel() {
        Hotel hotel = testHotel();
        when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));
        HotelResponse res = hotelService.getHotel(1);
        assertEquals("TestHotel", res.getName());
    }
    @Test
    void getHotelFail(){
        when(hotelRepository.findById(1)).thenReturn(Optional.empty());
        ResponseStatusException res = assertThrows(ResponseStatusException.class, () -> hotelService.getHotel(1));
        assertEquals("Hotel not found", res.getReason());
    }

    @Test
    void deleteHotelFail() {
        lenient().when(hotelRepository.existsById(1)).thenReturn(false);

        ResponseStatusException res = assertThrows(ResponseStatusException.class, () -> hotelService.deleteHotel(1));

        assertEquals("Hotel not found", res.getReason());
    }
    @Test
    void deleteHotelSuccess(){
        Hotel hotel = testHotel();
        when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));
        String res = hotelService.deleteHotel(1);
        assertEquals("{\"message\":\"Hotel deleted\"}", res);
    }
}