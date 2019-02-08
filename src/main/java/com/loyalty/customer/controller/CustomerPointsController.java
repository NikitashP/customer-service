package com.loyalty.customer.controller;

import com.loyalty.customer.command.AddPointsCommand;
import com.loyalty.customer.command.DeductPointsCommand;
import com.loyalty.customer.command.InitializeCustomerCommand;
import com.loyalty.customer.controller.request.PointsRequest;
import com.loyalty.customer.query.CustomerQueryHandler;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.common.IdentifierFactory;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


@RestController
@RequiredArgsConstructor
public class CustomerPointsController {

    private final IdentifierFactory identifierFactory = IdentifierFactory.getInstance();

    private final CommandGateway commandGateway;

    private final CustomerQueryHandler customerQueryHandler;

    @PostMapping("/create")
    public CompletableFuture<String> createCustomerWithPoints(@RequestBody PointsRequest pointsRequest){
        String customerId;
        if(Objects.isNull(pointsRequest.getId())){
            customerId=identifierFactory.generateIdentifier();
        }else {
            customerId=pointsRequest.getId();
        }
        return commandGateway.send(new InitializeCustomerCommand(customerId,pointsRequest.getPoints()));
    }

    @PutMapping("/points/add")
    public void addPointsToCustomer(@Valid @RequestBody PointsRequest pointsRequest){
        commandGateway.send(new AddPointsCommand(pointsRequest.getId(),pointsRequest.getPoints()));
    }

    @PutMapping("/points/deduct")
    public void deductPointsFromCustomer(@Valid @RequestBody PointsRequest pointsRequest){
        commandGateway.send(new DeductPointsCommand(pointsRequest.getId(),pointsRequest.getPoints()));
    }

    @GetMapping("/points/{id}")
    public long getCustomerPoints(@NonNull @PathVariable("id") String customerId){
        return customerQueryHandler.getCustomerPoints(customerId);
    }

    @GetMapping("/events/{id}")
    public List<Object> getAllChangesToCustomer(@NonNull @PathVariable("id") String customerId){
        return customerQueryHandler.getAllChangesForCustomer(customerId);
    }


    @ExceptionHandler(AggregateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidInput(IllegalArgumentException exception) {
        return exception.getMessage();
    }
}
