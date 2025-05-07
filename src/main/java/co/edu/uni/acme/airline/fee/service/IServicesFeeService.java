package co.edu.uni.acme.airline.fee.service;

import co.edu.uni.acme.aerolinea.commons.dto.ServiceDTO;
import co.edu.uni.acme.airline.fee.dto.ServiceExtraFlightResponseDto;

import java.util.List;

public interface IServicesFeeService {

    List<ServiceExtraFlightResponseDto> getAllFeeServices(String codeFlight);

}
