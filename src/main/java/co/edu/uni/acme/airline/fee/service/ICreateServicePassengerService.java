package co.edu.uni.acme.airline.fee.service;

import co.edu.uni.acme.airline.fee.dto.ServicePassengerRequestDto;

public interface ICreateServicePassengerService {

    boolean createServicePassenger(ServicePassengerRequestDto request);

}
