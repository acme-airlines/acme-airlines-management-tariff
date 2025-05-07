package co.edu.uni.acme.airline.fee.dto;

import lombok.Data;

@Data
public class FeeInformationBaseDto {

    private String codeFlight;

    private String codeFee;

    private String valueFee;

    private String nameFeeType;

}
