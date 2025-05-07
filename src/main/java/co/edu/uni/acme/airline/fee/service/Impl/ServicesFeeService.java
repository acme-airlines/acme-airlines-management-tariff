package co.edu.uni.acme.airline.fee.service.Impl;

import co.edu.uni.acme.aerolinea.commons.dto.ServiceDTO;
import co.edu.uni.acme.airline.fee.dto.ServiceExtraFlightResponseDto;
import co.edu.uni.acme.airline.fee.repository.ServiceFlightExtraRepository;
import co.edu.uni.acme.airline.fee.repository.ServicePassengerRepository;
import co.edu.uni.acme.airline.fee.service.IServicesFeeService;
import co.edu.uni.acme.airline.fee.util.mappers.ServiceFlightExtraMapper;
import co.edu.uni.acme.airline.fee.util.mappers.ServicePassengerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesFeeService implements IServicesFeeService {

    private final ServiceFlightExtraRepository serviceFlightExtraRepository;

    private final ServiceFlightExtraMapper serviceFlightExtraMapper;


    @Override
    public List<ServiceExtraFlightResponseDto> getAllFeeServices(String codeFlight) {
        return serviceFlightExtraMapper.objectToServiceList(serviceFlightExtraRepository.listServiceFlightExtra(codeFlight));
    }
}
