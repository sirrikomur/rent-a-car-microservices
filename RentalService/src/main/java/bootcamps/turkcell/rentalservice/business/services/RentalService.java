package bootcamps.turkcell.rentalservice.business.services;

import bootcamps.turkcell.common.utilities.dtos.rental.requests.rental.create.CreateRentalRequest;
import bootcamps.turkcell.common.utilities.dtos.rental.requests.rental.update.UpdateRentalRequest;
import bootcamps.turkcell.common.utilities.dtos.rental.responses.rental.create.CreateRentalResponse;
import bootcamps.turkcell.common.utilities.dtos.rental.responses.rental.get.GetAllRentalsResponse;
import bootcamps.turkcell.common.utilities.dtos.rental.responses.rental.get.GetRentalResponse;
import bootcamps.turkcell.common.utilities.dtos.rental.responses.rental.update.UpdateRentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(UUID id);
    GetRentalResponse finishRental(UUID id);
    CreateRentalResponse create(CreateRentalRequest rentalRequest);
    UpdateRentalResponse update(UUID id, UpdateRentalRequest rentalRequest);
    void delete(UUID id);
}
