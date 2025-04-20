package co.edu.uni.acme.airline.fee.repository;

import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightEntity, String> {
}
