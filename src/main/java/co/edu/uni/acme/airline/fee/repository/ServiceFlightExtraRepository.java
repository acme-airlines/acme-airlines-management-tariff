package co.edu.uni.acme.airline.fee.repository;

import co.edu.uni.acme.aerolinea.commons.entity.ServiceFlightExtraEntity;
import co.edu.uni.acme.aerolinea.commons.entity.ServicePassengerExtraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface ServiceFlightExtraRepository extends JpaRepository<ServiceFlightExtraEntity, String> {

    @Query(value = """
        select sfe.code_service_fk, sfe.quantity, s.value_service, s.name_service
                from acme_airlines.service_flight_extra sfe
                inner join acme_airlines.service s on sfe.code_service_fk = s.code_service
                where sfe.code_flight_fk = :codeFlight
    """, nativeQuery = true)
    List<Object[]> listServiceFlightExtra(@Param("codeFlight") String codeFlight);

    @Modifying
    @Query(value = """
        update acme_airlines.service_flight_extra  set quantity = :newQuantity where code_flight_fk = :codeFlightFk and code_service_fk = :codeServiceFk
    """, nativeQuery = true)
    void updateQuantity(@Param("newQuantity") Long newQuantity, @Param("codeFlightFk") String codeFlightFk, @Param("codeServiceFk") String codeServiceFk);

    ServiceFlightExtraEntity findByCodeServiceFk_codeServiceAndCodeFlightFk_codeFlight(String codeService, String codeFlight);

}
