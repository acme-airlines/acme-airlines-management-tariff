package co.edu.uni.acme.airline.fee.util.mappers;

import co.edu.uni.acme.aerolinea.commons.dto.FlightDTO;
import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import co.edu.uni.acme.aerolinea.commons.entity.PassengerEntity;
import co.edu.uni.acme.airline.fee.dto.FeesFlightDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightDTO entityToDto(FlightEntity entity);

}