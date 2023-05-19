package bootcamps.turkcell.rentalservice.api.controllers;

import bootcamps.turkcell.rentalservice.business.dtos.requests.rental.create.CreateRentalRequest;
import bootcamps.turkcell.rentalservice.business.dtos.requests.rental.update.UpdateRentalRequest;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.create.CreateRentalResponse;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.get.GetAllRentalsResponse;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.get.GetRentalResponse;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.update.UpdateRentalResponse;
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

    @PutMapping("/finish")
    public GetRentalResponse finishRental(@RequestParam UUID carId) {
        return service.finishRental(carId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}