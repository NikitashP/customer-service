package com.loyalty.customer.command;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class DeductPointsCommand extends BaseCommand {


    public DeductPointsCommand(String id, long points) {
        super(id, points);
    }
}
