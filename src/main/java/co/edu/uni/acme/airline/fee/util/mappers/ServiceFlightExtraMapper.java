package co.edu.uni.acme.airline.fee.util.mappers;

import co.edu.uni.acme.aerolinea.commons.dto.ServiceDTO;
import co.edu.uni.acme.aerolinea.commons.dto.ServiceFlightExtraDTO;
import co.edu.uni.acme.aerolinea.commons.dto.ServicePassengerExtraDto;
import co.edu.uni.acme.aerolinea.commons.entity.ServiceFlightExtraEntity;
import co.edu.uni.acme.aerolinea.commons.entity.ServicePassengerExtraEntity;
import co.edu.uni.acme.airline.fee.dto.ServiceExtraFlightResponseDto;
import co.edu.uni.acme.airline.fee.util.helper.Helpers;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceFlightExtraMapper {

    ServiceFlightExtraDTO toDto(ServiceFlightExtraEntity entity);

    ServiceFlightExtraEntity toEntity(ServiceFlightExtraDTO entity);


    default List<ServiceExtraFlightResponseDto> objectToServiceList(List<Object[]> listData){
        List<ServiceExtraFlightResponseDto> services = new ArrayList<>();
        for(Object[] data : listData){
            ServiceExtraFlightResponseDto service = new ServiceExtraFlightResponseDto();
            service.setCodeService(Helpers.checkType(data[0], String.class).orElse(null));
            service.setQuantity(Helpers.checkType(data[1], Long.class).orElse(null));
            service.setValue(Helpers.checkType(data[2], Double.class).orElse(null));
            service.setNameService(Helpers.checkType(data[3], String.class).orElse(null));
            services.add(service);
        }
        return services;
    }

}
