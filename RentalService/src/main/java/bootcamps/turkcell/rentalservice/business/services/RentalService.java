package bootcamps.turkcell.rentalservice.business.services;


import bootcamps.turkcell.rentalservice.business.dtos.requests.rental.create.CreateRentalRequest;
import bootcamps.turkcell.rentalservice.business.dtos.requests.rental.update.UpdateRentalRequest;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.create.CreateRentalResponse;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.get.GetAllRentalsResponse;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.get.GetRentalResponse;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.update.UpdateRentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(UUID id);
    GetRentalResponse finishRental(UUID carId);
    CreateRentalResponse create(CreateRentalRequest rentalRequest);
    UpdateRentalResponse update(UUID id, UpdateRentalRequest rentalRequest);
    void delete(UUID id);
}
