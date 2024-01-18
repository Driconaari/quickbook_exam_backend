package dk.kea.quickbook.api;

import dk.kea.quickbook.dto.ReservationRequest;
import dk.kea.quickbook.dto.ReservationResponse;
import dk.kea.quickbook.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    //Guest
    @PostMapping()
    public ReservationResponse createReservation(@RequestBody ReservationRequest reservationRequest, Principal principal){
        System.out.println(reservationRequest.getReservationDate());
        return reservationService.createReservation(reservationRequest, principal.getName());
    }
    //Guest
    @GetMapping("/reservations-for-guest")
    public List<ReservationResponse> getAllReservationsForGuest(Principal principal){
        return reservationService.getAllReservationsForGuest(principal.getName());
    }
    //Guest
    @DeleteMapping("/guest-as-authenticated")
    public String deleteReservation(@RequestBody ReservationRequest reservationRequest, Principal principal){
        return reservationService.deleteReservation(reservationRequest, principal.getName());
    }
}
