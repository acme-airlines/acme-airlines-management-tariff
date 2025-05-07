package co.edu.uni.acme.airline.fee.dto;

import lombok.Data;

import java.util.List;

@Data
public class FeesFlightDto {

    private String codeFlight;

    private List<FeesServiceDto> fees;

}
