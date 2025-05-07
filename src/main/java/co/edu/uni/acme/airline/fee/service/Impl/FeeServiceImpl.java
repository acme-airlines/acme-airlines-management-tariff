package co.edu.uni.acme.airline.fee.service.Impl;

import co.edu.uni.acme.aerolinea.commons.dto.FeeDTO;
import co.edu.uni.acme.aerolinea.commons.dto.FlightDTO;
import co.edu.uni.acme.aerolinea.commons.dto.ServiceDTO;
import co.edu.uni.acme.aerolinea.commons.entity.FeeEntity;
import co.edu.uni.acme.aerolinea.commons.entity.FlightEntity;
import co.edu.uni.acme.airline.fee.dto.FeeInformationBaseDto;
import co.edu.uni.acme.airline.fee.dto.FeesFlightDto;
import co.edu.uni.acme.airline.fee.dto.FeesServiceDto;
import co.edu.uni.acme.airline.fee.repository.FeeRepository;
import co.edu.uni.acme.airline.fee.repository.FlightRepository;
import co.edu.uni.acme.airline.fee.service.IFeeService;
import co.edu.uni.acme.airline.fee.util.mappers.FeeMapper;
import co.edu.uni.acme.airline.fee.util.mappers.FlightMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements IFeeService {

    private final FeeRepository feeRepository;

    private final FeeMapper feeMapper;

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    @Override
    public List<FeeDTO> getAllFees() {
        return feeMapper.listEntityToListDto(feeRepository.findAll());
    }

    @Override
    public List<FeeDTO> getAvailableFeesByDate(LocalDate date) {
        return feeMapper.listEntityToListDto(feeRepository.findAvailableByDate(date));
    }

    @Override
    public List<FeeDTO> getRefundableFees() {
        return feeMapper.listEntityToListDto(
                feeRepository.findAll()
                        .stream()
                        .filter(FeeEntity::isRefundable)
                        .toList()
        );
    }

    @Override
    public List<FeeDTO> getByFeeType(String feeTypeCode) {
        return feeMapper.listEntityToListDto(
                feeRepository.findAll()
                        .stream()
                        .filter(fee -> fee.getNameFeeType().getCodeFeeType().equalsIgnoreCase(feeTypeCode))
                        .toList()
        );
    }

    @Override
    public FeesFlightDto getFeesForFlight(String flightCode) {
        FlightDTO flight = flightRepository.findById(flightCode)
                .map(flightMapper::entityToDto)
                .orElseThrow(() -> new IllegalArgumentException("Vuelo no encontrado"));
        List<FeeInformationBaseDto> tarifasDisponibles = feeMapper.objectToFeeInformationBase(feeRepository.buscarTarifasVuelos(flight.getCodeFlight()));
        List<FeesServiceDto> feesServiceDtos = new ArrayList<>();
        for(FeeInformationBaseDto informationBaseDto : tarifasDisponibles){
            FeesServiceDto feesServiceDto = new FeesServiceDto();
            feesServiceDto.setCodigoFee(informationBaseDto.getCodeFee());
            feesServiceDto.setNameFeeType(informationBaseDto.getNameFeeType());
            feesServiceDto.setValueFee(informationBaseDto.getValueFee());
            feesServiceDto.setServices(feeMapper.objectToServiceList(feeRepository.buscarServiciosTarifa(informationBaseDto.getCodeFee())));
            feesServiceDtos.add(feesServiceDto);
        }
        FeesFlightDto feesFlightDto = new FeesFlightDto();
        feesFlightDto.setCodeFlight(flightCode);
        feesFlightDto.setFees(feesServiceDtos);
        return feesFlightDto;
    }

    @Override
    public boolean isFeeValidForFlight(String flightCode, String feeCode) {
        FlightEntity flight = flightRepository.findById(flightCode)
                .orElseThrow(() -> new IllegalArgumentException("Vuelo no encontrado"));

        LocalDate flightDate = flight.getFlightDate();

        return feeRepository.findById(feeCode)
                .filter(FeeEntity::isAvailable)
                .filter(fee -> {
                    LocalDate from = fee.getAvailableFrom();
                    LocalDate to = fee.getAvailableTo();
                    return (flightDate.isEqual(from) || flightDate.isAfter(from)) &&
                            (flightDate.isEqual(to) || flightDate.isBefore(to));
                })
                .isPresent();
    }

}
