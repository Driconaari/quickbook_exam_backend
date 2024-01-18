package dk.kea.quickbook.repository;

import dk.kea.quickbook.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
//    Select from hotel where lowercase h.type like lowercase filter - used for pagination
//    @Query("SELECT h FROM Hotel h " + "WHERE LOWER(h.type) LIKE LOWER(:filter) ")
//    Page<Hotel> findAll(String filter, Pageable pageable);
}
