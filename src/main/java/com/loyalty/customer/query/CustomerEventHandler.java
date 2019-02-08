package com.loyalty.customer.query;

import com.loyalty.customer.event.AddPointsEvent;
import com.loyalty.customer.event.DeductPointsEvent;
import com.loyalty.customer.event.InitializeCustomerEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventHandler {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerEventHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @EventHandler
    void on(InitializeCustomerEvent initializeCustomerEvent){
    customerRepository.save(new Customer(initializeCustomerEvent.getId(),initializeCustomerEvent.getPoints()));
    }


    @EventHandler
    void on(AddPointsEvent addPointsEvent){
        customerRepository.findById(addPointsEvent.getId()).ifPresent(customer -> {
            customer.setPoints(customer.getPoints()+addPointsEvent.getPoints());
            customerRepository.save(customer);
        });
    }

    @EventHandler
    void on(DeductPointsEvent deductPointsEvent){
        customerRepository.findById(deductPointsEvent.getId()).ifPresent(customer -> {
            customer.setPoints(customer.getPoints()-deductPointsEvent.getPoints());
            customerRepository.save(customer);
        });
    }

}
