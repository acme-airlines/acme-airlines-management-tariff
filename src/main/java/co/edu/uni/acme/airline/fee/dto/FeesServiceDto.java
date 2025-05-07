package co.edu.uni.acme.airline.fee.dto;

import co.edu.uni.acme.aerolinea.commons.dto.ServiceDTO;
import lombok.Data;

import java.util.List;

@Data
public class FeesServiceDto {

    private String codigoFee;

    private String nameFeeType;

    private String valueFee;

    private List<ServiceDTO> services;

}
