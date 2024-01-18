package dk.kea.quickbook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.quickbook.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor //Never used.
@NoArgsConstructor //Never used.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelRequest {
    private int id;
    private String name;
    private String street;
    private String city;
    private int zip;
    private String country;

    public HotelRequest(String name, String street, String city, int zip, String country){ // Constructor for testing. Could have used builder.
        this.name = name;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }
    public static Hotel getHotelEntity(HotelRequest hotelRequest){
        return new Hotel(
                hotelRequest.name,
                hotelRequest.street,
                hotelRequest.city,
                hotelRequest.zip,
                hotelRequest.country
        );
    }
}
