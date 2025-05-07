package co.edu.uni.acme.airline.fee.controller;

import co.edu.uni.acme.airline.fee.dto.ServiceExtraFlightResponseDto;
import co.edu.uni.acme.airline.fee.service.IServicesFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service-fee")
@RequiredArgsConstructor
public class ServiceFeeController {

    private final IServicesFeeService iServicesFeeService;

    @GetMapping("/all")
    public ResponseEntity<List<ServiceExtraFlightResponseDto>> getAllServiceExtra(
            @RequestParam("flightCode") String codeFlight ) {
        return ResponseEntity.ok(iServicesFeeService.getAllFeeServices(codeFlight));
    }
}
