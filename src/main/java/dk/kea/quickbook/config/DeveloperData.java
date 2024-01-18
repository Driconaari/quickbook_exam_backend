package dk.kea.quickbook.config;

import dk.kea.quickbook.entity.Guest;
import dk.kea.quickbook.entity.Hotel;
import dk.kea.quickbook.entity.Reservation;
import dk.kea.quickbook.entity.Room;
import dk.kea.quickbook.repository.GuestRepository;
import dk.kea.quickbook.repository.HotelRepository;
import dk.kea.quickbook.repository.ReservationRepository;
import dk.kea.quickbook.repository.RoomRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static dk.kea.security.entity.Role.ADMIN;
import static dk.kea.security.entity.Role.GUEST;

@Configuration
public class DeveloperData implements ApplicationRunner {
    GuestRepository guestRepository;
    HotelRepository hotelRepository;
    RoomRepository roomRepository;
    ReservationRepository reservationRepository;

    public DeveloperData(GuestRepository guestRepository, HotelRepository hotelRepository, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.guestRepository = guestRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Random random = new Random();
        Guest admin1 = new Guest("admin", "admin@admin", "password123", "Admin", "Admin", "45454545");
        admin1.addRole(ADMIN);
        guestRepository.save(admin1);
        //Chat gpt generated names - code by me
        ArrayList<String> firstNames = new ArrayList<>(Arrays.asList(
                "John", "Emily", "Michael", "Sophia", "Daniel",
                "Olivia", "Christopher", "Ava", "Matthew", "Emma",
                "Andrew", "Madison", "Ethan", "Abigail", "William",
                "Isabella", "James", "Grace", "Benjamin", "Mia"
        ));
        //Chat gpt generated names  - code by me
        ArrayList<String> lastNames = new ArrayList<>(Arrays.asList(
                "Smith", "Johnson", "Williams", "Jones", "Brown",
                "Davis", "Miller", "Wilson", "Moore", "Taylor",
                "Anderson", "Thomas", "Jackson", "White", "Harris",
                "Martin", "Thompson", "Garcia", "Martinez", "Robinson"
        ));
        for (int i = 1; i <= 9; i++) {
            String username = "guest" + i;
            String email = "guest_email" + i + "@guest.dk";
            String firstName = firstNames.get(random.nextInt(firstNames.size()));
            String lastName = lastNames.get(random.nextInt(lastNames.size()));
            String phoneNumber = String.valueOf(random.nextInt(10000000, 99999999));
            Guest guest = new Guest(username, email, "password123", firstName, lastName, phoneNumber);
            guest.addRole(GUEST);
            guestRepository.save(guest);
        }
        //Chat gpt generated names  - code by me
        ArrayList<String> countries = new ArrayList<>(Arrays.asList(
                "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria",
                "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
                "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia",
                "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica",
                "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador",
                "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France",
                "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau",
                "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel",
                "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South",
                "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania",
                "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius",
                "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru",
                "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Macedonia", "Norway", "Oman", "Pakistan",
                "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania",
                "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia",
                "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain",
                "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand",
                "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
                "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia",
                "Zimbabwe", "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria",
                "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
                "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia",
                "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica",
                "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador",
                "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia"
        ));
        //Chat gpt generated names  - code by me
        ArrayList<String> cities = new ArrayList<>(Arrays.asList(
                "Kabul", "Tirana", "Algiers", "Andorra la Vella", "Luanda",
                "St. John's", "Buenos Aires", "Yerevan", "Sydney", "Vienna",
                "Baku", "Nassau", "Manama", "Dhaka", "Bridgetown",
                "Minsk", "Brussels", "Belize City", "Cotonou", "Thimphu",
                "Sucre", "Sarajevo", "Gaborone", "Sao Paulo", "Bandar Seri Begawan",
                "Sofia", "Ouagadougou", "Bujumbura", "Praia", "Phnom Penh",
                "Yaounde", "Ottawa", "Bangui", "N'Djamena", "Santiago",
                "Beijing", "Bogota", "Moroni", "Brazzaville", "San Jose",
                "Zagreb", "Havana", "Nicosia", "Prague", "Copenhagen",
                "Djibouti", "Roseau", "Santo Domingo", "Dili", "Quito",
                "Cairo", "San Salvador", "Malabo", "Asmara", "Tallinn",
                "Mbabane", "Addis Ababa", "Suva", "Helsinki", "Paris",
                "Libreville", "Banjul", "Tbilisi", "Berlin", "Accra",
                "Athens", "St. George's", "Guatemala City", "Conakry", "Bissau",
                "Georgetown", "Port-au-Prince", "Tegucigalpa", "Budapest", "Reykjavik",
                "New Delhi", "Jakarta", "Tehran", "Baghdad", "Dublin",
                "Jerusalem", "Rome", "Yamoussoukro", "Kingston", "Tokyo",
                "Amman", "Astana", "Nairobi", "Tarawa", "Pyongyang",
                "Seoul", "Pristina", "Kuwait City", "Bishkek", "Vientiane",
                "Riga", "Beirut", "Maseru", "Monrovia", "Tripoli",
                "Vaduz", "Vilnius", "Luxembourg City", "Antananarivo", "Lilongwe",
                "Kuala Lumpur", "Male", "Bamako", "Valletta", "Majuro",
                "Nouakchott", "Port Louis", "Mexico City", "Palikir", "Chisinau",
                "Monaco", "Ulaanbaatar", "Podgorica", "Rabat", "Maputo",
                "Naypyidaw", "Windhoek", "Yaren", "Kathmandu", "Amsterdam",
                "Wellington", "Managua", "Niamey", "Abuja", "Pyongyang",
                "Oslo", "Muscat", "Islamabad", "Melekeok", "Panama City",
                "Port Moresby", "Asuncion", "Lima", "Manila", "Warsaw",
                "Lisbon", "Doha", "Bucharest", "Moscow", "Kigali",
                "Basseterre", "Castries", "Kingstown", "Apia", "San Marino",
                "Sao Tome", "Riyadh", "Dakar", "Belgrade", "Victoria",
                "Freetown", "Singapore", "Bratislava", "Ljubljana", "Honiara",
                "Mogadishu", "Pretoria", "Juba", "Madrid", "Colombo",
                "Khartoum", "Paramaribo", "Stockholm", "Bern", "Damascus",
                "Taipei", "Dushanbe", "Dodoma", "Bangkok", "Lome",
                "Nuku'alofa", "Port of Spain", "Tunis", "Ankara", "Ashgabat",
                "Funafuti", "Kampala", "Kyiv", "Abu Dhabi", "London",
                "Washington, D.C.", "Montevideo", "Tashkent", "Port Vila", "Vatican City",
                "Caracas", "Hanoi", "Sana'a", "Lusaka", "Harare",
                "Kabul", "Tirana", "Algiers", "Andorra la Vella", "Luanda",
                "St. John's", "Buenos Aires", "Yerevan", "Sydney", "Vienna",
                "Baku", "Nassau", "Manama", "Dhaka", "Bridgetown",
                "Minsk", "Brussels", "Belize City", "Cotonou", "Thimphu",
                "Sucre", "Sarajevo", "Gaborone", "Sao Paulo", "Bandar Seri Begawan",
                "Sofia", "Ouagadougou", "Bujumbura", "Praia", "Phnom Penh",
                "Yaounde", "Ottawa", "Bangui", "N'Djamena", "Santiago",
                "Beijing", "Bogota", "Moroni", "Brazzaville", "San Jose",
                "Zagreb", "Havana", "Nicosia", "Prague", "Copenhagen",
                "Djibouti", "Roseau", "Santo Domingo", "Dili", "Quito",
                "Cairo", "San Salvador", "Malabo", "Asmara", "Tallinn"
        ));
        //Chat gpt generated names  - code by me
        ArrayList<String> streets = new ArrayList<>(Arrays.asList(
                "Boulevard", "Street", "Road", "Avenue", "Lane",
                "Drive", "Place", "Square", "Court", "Parkway"
        ));
        ArrayList<String> hotelNames = new ArrayList<>(Arrays.asList(
                "Grand", "Royal", "Luxury", "Plaza", "Park"
        ));
        for (int i = 0; i < 250; i++) { //Ingen grund til -1 i mine felter, når jeg starter på 0 op til 249.
            String name = "Hotel "+ hotelNames.get(random.nextInt(hotelNames.size()-1));
            String street = streets.get(random.nextInt(streets.size()-1));
            String country = countries.get(i);
            String city = cities.get(random.nextInt(cities.size()-1));
            int zip = random.nextInt(1000, 9999);
            Hotel hotel = new Hotel(name, street, city, zip, country);
            hotelRepository.save(hotel);
            int numberOfRooms = random.nextInt(31) + 10; //0-30=31 tal og +10 starting point rykket.
            for (int j = 1; j <= numberOfRooms; j++) {
                Room room = new Room(j, random.nextInt(4) + 1, random.nextInt(1000) + 500, hotel);
                roomRepository.save(room);
            }
        }
        Guest guest = new Guest("TestForReservations", "test@test", "password123", "TestFN", "TestLN", "12345678");
        guest.addRole(GUEST);
        guestRepository.save(guest);
        Hotel hotel = new Hotel("TestHotel", "TestStreet", "TestCity", 1234, "TestCountry");
        hotelRepository.save(hotel);
        Room room = new Room(1, 1, 1000, hotel);
        roomRepository.save(room);
        Reservation reservation = new Reservation(LocalDate.now(), guest, room);
        Reservation reservation1 = new Reservation(LocalDate.now().plusDays(1), guest, room);
        Reservation reservation2 = new Reservation(LocalDate.now().plusDays(2), guest, room);
        reservationRepository.save(reservation);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
    }
}