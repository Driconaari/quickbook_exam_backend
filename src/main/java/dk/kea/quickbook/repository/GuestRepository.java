package dk.kea.quickbook.repository;

import dk.kea.quickbook.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository is a Spring Data interface for generic CRUD operations on a repository of a specific type, and pagination.
public interface GuestRepository extends JpaRepository<Guest, String> {
    boolean existsByEmail(String email);
}
