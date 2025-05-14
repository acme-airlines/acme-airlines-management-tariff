package co.edu.uni.acme.airline.fee.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ServicePassengerRequestDto {

    private String codeFlight;

    private Map<String, List<ServiceExtDto>> servicesByPassenger;

    private Map<String,String> codePassengers;

}
