package com.loyalty.customer.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Value
public class InitializeCustomerEvent extends BaseEvent{

    public InitializeCustomerEvent(String id, long points) {
        super(id, points, "initialize customer points", Instant.now());
    }
}
