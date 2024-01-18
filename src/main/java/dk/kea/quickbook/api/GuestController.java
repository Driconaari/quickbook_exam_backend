package dk.kea.quickbook.api;

import dk.kea.quickbook.dto.GuestRequest;
import dk.kea.quickbook.dto.GuestResponse;
import dk.kea.quickbook.service.GuestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }
    //Anonymous
    @PostMapping()
    public GuestResponse createGuest(@RequestBody GuestRequest guestRequest) {
        return guestService.createGuest(guestRequest);
    }
}
