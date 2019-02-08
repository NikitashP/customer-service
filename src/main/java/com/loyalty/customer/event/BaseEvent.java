package com.loyalty.customer.event;


import lombok.Getter;
import lombok.NonNull;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

public abstract class BaseEvent {

    @Getter
    @TargetAggregateIdentifier
    protected final String id;

    @Getter
    @NotNull
    protected final long points;

    @Getter
    @NonNull
    protected final String message;

    @Getter
    @NonNull
    private final Instant creationTime;

    protected BaseEvent(String id, @NotNull long points, String message, Instant creationTime) {
        this.id = id;
        this.points = points;
        this.message = message;
        this.creationTime = creationTime.truncatedTo(ChronoUnit.SECONDS);
    }
}
