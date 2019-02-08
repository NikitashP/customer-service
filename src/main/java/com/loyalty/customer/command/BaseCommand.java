package com.loyalty.customer.command;

import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

public abstract class BaseCommand {
    @Getter
    @TargetAggregateIdentifier
    protected final String id;
    @Getter
    @NotNull
    protected final long points;

    protected BaseCommand(String id, long points) {
        this.id = id;
        this.points = points;
    }
}
