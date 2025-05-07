package co.edu.uni.acme.airline.fee.service;

import co.edu.uni.acme.aerolinea.commons.dto.FeeDTO;
import co.edu.uni.acme.airline.fee.dto.FeesFlightDto;

import java.time.LocalDate;
import java.util.List;

public interface IFeeService {

    List<FeeDTO> getAllFees();

    List<FeeDTO> getAvailableFeesByDate(LocalDate date);

    List<FeeDTO> getRefundableFees();

    List<FeeDTO> getByFeeType(String feeTypeCode);

    FeesFlightDto getFeesForFlight(String flightCode);

    boolean isFeeValidForFlight(String flightCode, String feeCode);

}
