package com.loyalty.customer.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Instant;


@EqualsAndHashCode(callSuper = true)
@Value
public class AddPointsEvent extends BaseEvent{

    public AddPointsEvent(String id, long points) {
        super(id, points, "add points", Instant.now());
    }
}
