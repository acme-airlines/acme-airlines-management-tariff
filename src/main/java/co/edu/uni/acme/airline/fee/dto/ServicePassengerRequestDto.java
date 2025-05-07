package co.edu.uni.acme.airline.fee.dto;

import lombok.Data;

import java.util.List;

@Data
public class ServicePassengerRequestDto {

    private String codeFlight;

    private List<ServiceExtDto> service;

    private List<String> codePassengers;

}
