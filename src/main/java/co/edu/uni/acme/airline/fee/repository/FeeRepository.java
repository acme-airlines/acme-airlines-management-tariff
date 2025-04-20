package co.edu.uni.acme.airline.fee.repository;

import co.edu.uni.acme.aerolinea.commons.entity.FeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FeeRepository extends JpaRepository<FeeEntity, String> {

    @Query("SELECT f FROM FeeEntity f " +
            "WHERE f.available = true AND :date BETWEEN f.availableFrom AND f.availableTo")
    List<FeeEntity> findAvailableByDate(LocalDate date);
}
