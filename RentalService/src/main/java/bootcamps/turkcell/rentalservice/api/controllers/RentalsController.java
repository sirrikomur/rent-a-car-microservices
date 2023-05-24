package bootcamps.turkcell.rentalservice.api.controllers;

import bootcamps.turkcell.common.utilities.dtos.rental.requests.rental.create.CreateRentalRequest;
import bootcamps.turkcell.common.utilities.dtos.rental.requests.rental.update.UpdateRentalRequest;
import bootcamps.turkcell.common.utilities.dtos.rental.responses.rental.create.CreateRentalResponse;
import bootcamps.turkcell.common.utilities.dtos.rental.responses.rental.get.GetAllRentalsResponse;
import bootcamps.turkcell.common.utilities.dtos.rental.responses.rental.get.GetRentalResponse;
import bootcamps.turkcell.common.utilities.dtos.rental.responses.rental.update.UpdateRentalResponse;
import bootcamps.turkcell.rentalservice.business.services.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rentals")
public class RentalsController {
    private final RentalService service;

    @GetMapping
    public List<GetAllRentalsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CreateRentalResponse create(@RequestBody CreateRentalRequest rentalRequest) {
        return service.create(rentalRequest);
    }

    @PutMapping("/{id}")
    public UpdateRentalResponse update(@PathVariable UUID id, @RequestBody UpdateRentalRequest rentalRequest) {
        return service.update(id, rentalRequest);
    }

    @PutMapping("/{id}/finish")
    public GetRentalResponse finishRental(@PathVariable UUID id) {
        return service.finishRental(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}