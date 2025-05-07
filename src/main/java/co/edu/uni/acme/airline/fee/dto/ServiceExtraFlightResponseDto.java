package co.edu.uni.acme.airline.fee.dto;

import lombok.Data;

@Data
public class ServiceExtraFlightResponseDto {

    private String codeService;

    private Long quantity;

    private Double value;

    private String nameService;

}
