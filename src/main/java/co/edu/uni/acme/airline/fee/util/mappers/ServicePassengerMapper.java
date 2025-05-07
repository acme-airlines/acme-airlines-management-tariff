package co.edu.uni.acme.airline.fee.util.mappers;

import co.edu.uni.acme.aerolinea.commons.dto.ServicePassengerExtraDto;
import co.edu.uni.acme.aerolinea.commons.dto.ServicePassengerExtraIdDto;
import co.edu.uni.acme.aerolinea.commons.entity.ServicePassengerExtraEntity;
import co.edu.uni.acme.aerolinea.commons.entity.ServicePassengerExtraId;
import co.edu.uni.acme.airline.fee.dto.ServicePassengerRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicePassengerMapper {

    ServicePassengerExtraDto toDto(ServicePassengerExtraEntity entity);

    ServicePassengerExtraEntity toEntity(ServicePassengerExtraDto dto);

    ServicePassengerExtraIdDto toDto(ServicePassengerExtraId entity);

    ServicePassengerExtraId toEntity(ServicePassengerExtraIdDto dto);


}
