package co.edu.uni.acme.airline.fee.controller;

import co.edu.uni.acme.airline.fee.dto.RespuestaDto;
import co.edu.uni.acme.airline.fee.dto.ServicePassengerRequestDto;
import co.edu.uni.acme.airline.fee.service.ICreateServicePassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/service-passenger")
@RequiredArgsConstructor
public class ServicePassengerController {

    private final ICreateServicePassengerService iCreateServicePassengerService;

    @PostMapping("create")
    public ResponseEntity<RespuestaDto> createServicePassenger(@RequestBody ServicePassengerRequestDto request) {
        boolean isCreate = iCreateServicePassengerService.createServicePassenger(request);
        RespuestaDto respuestaDto = new RespuestaDto();
        respuestaDto.setMessage(isCreate ? "Se creo correctamente el passenger" : "No se creo el passager");
        return ResponseEntity.status(isCreate ? 200 : 400).body(respuestaDto);
    }

}
