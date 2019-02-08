package com.loyalty.customer.command;


import lombok.EqualsAndHashCode;
import lombok.Value;


@EqualsAndHashCode(callSuper = true)
@Value
public class AddPointsCommand extends BaseCommand {
    public AddPointsCommand(String id, long points) {
        super(id, points);
    }
}
