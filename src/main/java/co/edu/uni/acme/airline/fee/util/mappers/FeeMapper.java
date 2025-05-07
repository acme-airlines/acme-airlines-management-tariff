package co.edu.uni.acme.airline.fee.util.mappers;

import co.edu.uni.acme.aerolinea.commons.dto.FeeDTO;
import co.edu.uni.acme.aerolinea.commons.dto.ServiceDTO;
import co.edu.uni.acme.aerolinea.commons.entity.FeeEntity;
import co.edu.uni.acme.airline.fee.dto.FeeInformationBaseDto;
import co.edu.uni.acme.airline.fee.util.helper.Helpers;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FeeMapper {

    FeeDTO entityToDto(FeeEntity entity);

    FeeEntity dtoToEntity(FeeDTO dto);

    List<FeeDTO> listEntityToListDto(List<FeeEntity> entity);

    List<FeeEntity> listDtoToEntity(List<FeeDTO> dto);

    default List<FeeInformationBaseDto> objectToFeeInformationBase(List<Object[]> listData){
        List<FeeInformationBaseDto> informationBaseDtos = new ArrayList<>();
        for(Object[] data : listData){
            FeeInformationBaseDto baseDto = new FeeInformationBaseDto();
            baseDto.setCodeFlight(Helpers.checkType(data[0], String.class).orElse(null));
            baseDto.setCodeFee(Helpers.checkType(data[1], String.class).orElse(null));
            baseDto.setNameFeeType(Helpers.checkType(data[2], String.class).orElse(null));
            baseDto.setValueFee(Helpers.checkType(data[3], String.class).orElse(null));
            informationBaseDtos.add(baseDto);
        }
        return  informationBaseDtos;
    }

    default List<ServiceDTO> objectToServiceList(List<Object[]> listData){
        List<ServiceDTO> services = new ArrayList<>();
        for(Object[] data : listData){
            ServiceDTO serviceDTO = new ServiceDTO();
            serviceDTO.setNameService(Helpers.checkType(data[0], String.class).orElse(null));
            serviceDTO.setValueService(Helpers.checkType(data[1], String.class).orElse(null));
            services.add(serviceDTO);
        }
        return services;
    }

}
