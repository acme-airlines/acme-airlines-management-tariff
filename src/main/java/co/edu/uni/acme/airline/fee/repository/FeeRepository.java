package co.edu.uni.acme.airline.fee.repository;

import co.edu.uni.acme.aerolinea.commons.entity.FeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<FeeEntity, String> {

    @Query("SELECT f FROM FeeEntity f " +
            "WHERE f.available = true AND :date BETWEEN f.availableFrom AND f.availableTo")
    List<FeeEntity> findAvailableByDate(LocalDate date);

    @Query(value = """
       select distinct ff.code_flight_fk,
            f.code_fee,
            ft.name_fee_type,
            f.value_fee from acme_airlines.flight_fee ff
            inner join acme_airlines.fee f on ff.code_fee_fk  = f.code_fee
            inner join acme_airlines.fee_service_type fst on ff.code_fee_fk = fst.code_fee_fk
            inner join acme_airlines.fee_type ft on f.code_fee_type_fk = ft.code_fee_type
            where ff.code_flight_fk  = :codigoVuelo
       """, nativeQuery = true)
    List<Object[]> buscarTarifasVuelos(@Param("codigoVuelo") String codigoVuelo);


    @Query(value = """
            select distinct s.name_service , s.value_service from acme_airlines.fee_service_type fst
            inner join acme_airlines.service s on fst.code_service_fk = s.code_service where fst.code_fee_fk = :codigoTarifa
    """, nativeQuery = true)
    List<Object[]> buscarServiciosTarifa(@Param("codigoTarifa") String codigoTarifa);

}
