package com.loyalty.customer.command;

import lombok.EqualsAndHashCode;
import lombok.Value;


@EqualsAndHashCode(callSuper = true)
@Value
public class InitializeCustomerCommand extends BaseCommand{

    public InitializeCustomerCommand(String id, long points) {
        super(id, points);
    }
}
