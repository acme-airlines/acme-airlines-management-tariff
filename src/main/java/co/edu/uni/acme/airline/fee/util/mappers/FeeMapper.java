package co.edu.uni.acme.airline.fee.util.mappers;

import co.edu.uni.acme.aerolinea.commons.dto.FeeDTO;
import co.edu.uni.acme.aerolinea.commons.entity.FeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeeMapper {

    FeeDTO entityToDto(FeeEntity entity);

    FeeEntity dtoToEntity(FeeDTO dto);

    List<FeeDTO> listEntityToListDto(List<FeeEntity> entity);

    List<FeeEntity> listDtoToEntity(List<FeeDTO> dto);
}
