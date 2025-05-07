package co.edu.uni.acme.airline.fee.controller;

import co.edu.uni.acme.aerolinea.commons.dto.FeeDTO;
import co.edu.uni.acme.airline.fee.dto.FeesFlightDto;
import co.edu.uni.acme.airline.fee.service.IFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fees")
@RequiredArgsConstructor
public class FeeController {

    private final IFeeService feeService;

    @GetMapping
    public ResponseEntity<List<FeeDTO>> getAllFees() {
        return ResponseEntity.ok(feeService.getAllFees());
    }

    @GetMapping("/available")
    public ResponseEntity<List<FeeDTO>> getAvailableFeesByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(feeService.getAvailableFeesByDate(date));
    }

    @GetMapping("/refundable")
    public ResponseEntity<List<FeeDTO>> getRefundableFees() {
        return ResponseEntity.ok(feeService.getRefundableFees());
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<FeeDTO>> getByFeeType(@RequestParam("type") String typeCode) {
        return ResponseEntity.ok(feeService.getByFeeType(typeCode));
    }
    @GetMapping("/available-by-flight")
    public ResponseEntity<FeesFlightDto> getFeesForFlight(@RequestParam("flightCode") String flightCode) {
        FeesFlightDto result = feeService.getFeesForFlight(flightCode);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateFeeForFlight(
            @RequestParam String flightCode,
            @RequestParam String feeCode
    ) {
        boolean isValid = feeService.isFeeValidForFlight(flightCode, feeCode);
        return ResponseEntity.ok(isValid);
    }


}
