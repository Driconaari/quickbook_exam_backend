package dk.kea.quickbook.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomRepositoryTest {

    @Mock
    RoomRepository roomRepository;
    @Test
    void existsByRoomNumberAndHotel_Id() {
        when(roomRepository.existsByRoomNumberAndHotel_Id(1,1)).thenReturn(true);
        boolean result = roomRepository.existsByRoomNumberAndHotel_Id(1,1);
        assertTrue(result);
    }
    @Test
    void existsByRoomNumberAndHotel_IdFail() {
        when(roomRepository.existsByRoomNumberAndHotel_Id(1,1)).thenReturn(false);
        boolean result = roomRepository.existsByRoomNumberAndHotel_Id(1,1);
        assertFalse(result);
    }

    @Test
    void findAllByHotelId() {
        when(roomRepository.findAllByHotelId(1)).thenReturn(null);
        assertNull(roomRepository.findAllByHotelId(1));
    }
}