package co.edu.uni.acme.airline.fee.service.Impl;

import co.edu.uni.acme.aerolinea.commons.dto.*;
import co.edu.uni.acme.airline.fee.dto.ServiceExtDto;
import co.edu.uni.acme.airline.fee.dto.ServicePassengerRequestDto;
import co.edu.uni.acme.airline.fee.repository.ServiceFlightExtraRepository;
import co.edu.uni.acme.airline.fee.repository.ServicePassengerRepository;
import co.edu.uni.acme.airline.fee.service.ICreateServicePassengerService;
import co.edu.uni.acme.airline.fee.util.mappers.ServiceFlightExtraMapper;
import co.edu.uni.acme.airline.fee.util.mappers.ServicePassengerMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import static co.edu.uni.acme.airline.fee.util.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class CreateServicePassengerService implements ICreateServicePassengerService {

    private final ServiceFlightExtraMapper    flightExtraMapper;
    private final ServiceFlightExtraRepository flightExtraRepo;
    private final ServicePassengerRepository   passengerServiceRepo;
    private final ServicePassengerMapper       passengerMapper;

    @Override
    @Transactional
    public boolean createServicePassenger(ServicePassengerRequestDto request) {
        String flightCode = request.getCodeFlight();
        Map<String, List<ServiceExtDto>> servicesMap = request.getServicesByPassenger();
        Map<String,String> passengerCodes = request.getCodePassengers();

        for(String key : passengerCodes.keySet()){
            String passengerCode  = passengerCodes.get(key);
            List<ServiceExtDto> servicesForThisPassenger = servicesMap.get(key);
            servicesForThisPassenger.forEach(ext ->
                    processAssignment(passengerCode, ext, flightCode)
            );
        }

        return true;
    }

    private void processAssignment(String passengerCode,
                                   ServiceExtDto ext,
                                   String flightCode) {

        ServiceFlightExtraDTO flightExtra = flightExtraMapper.toDto(
                flightExtraRepo
                        .findByCodeServiceFk_codeServiceAndCodeFlightFk_codeFlight(
                                ext.getCodeService(), flightCode
                        )
        );

        long requested = parseQuantity(ext.getQuantity());
        if (flightExtra.getQuantity() < requested) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    SERVICE_QUANTITY_EXCEEDS_CAPACITY
            );
        }

        assignService(passengerCode, ext.getCodeService(), Long.valueOf(ext.getQuantity()));
        flightExtraRepo.updateQuantity(flightExtra.getQuantity() - requested, flightCode, ext.getCodeService());
    }

    private long parseQuantity(String qty) {
        try {
            return Long.parseLong(qty);
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    INVALID_QUANTITY_FORMAT,
                    ex
            );
        }
    }

    private void assignService(String passengerCode,
                               String serviceCode,
                               Long quantityService) {

        ServicePassengerExtraIdDto idDto =
                new ServicePassengerExtraIdDto(passengerCode, serviceCode);

        ServicePassengerExtraDto dto = new ServicePassengerExtraDto();
        dto.setId(idDto);

        ServiceDTO svc = new ServiceDTO();
        svc.setCodeService(serviceCode);
        dto.setService(svc);

        PassengerDto p = PassengerDto.builder()
                .code(passengerCode)
                .build();
        dto.setPassenger(p);
        dto.setQuantity(quantityService);

        passengerServiceRepo.save(
                passengerMapper.toEntity(dto)
        );
    }
}
