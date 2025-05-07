package co.edu.uni.acme.airline.fee.repository;

import co.edu.uni.acme.aerolinea.commons.entity.ServicePassengerExtraEntity;
import co.edu.uni.acme.aerolinea.commons.entity.ServicePassengerExtraId;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePassengerRepository extends JpaRepository<ServicePassengerExtraEntity, ServicePassengerExtraId> {
}
