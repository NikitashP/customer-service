package com.loyalty.customer.query;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerQueryHandler {


    private final EventStore eventStore;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerQueryHandler(EventStore eventStore, CustomerRepository customerRepository) {
        this.eventStore = eventStore;
        this.customerRepository = customerRepository;
    }

    public long getCustomerPoints(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("unable to find customer with this Id"));
        return customer.getPoints();
    }


    public List<Object> getAllChangesForCustomer(String customerID){
        return eventStore.readEvents(customerID).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
    }
}
