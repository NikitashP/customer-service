package com.loyalty.customer.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Value
public class DeductPointsEvent extends BaseEvent{

    public DeductPointsEvent(String id, long points) {
        super(id, points, "deducted points", Instant.now());
    }
}
