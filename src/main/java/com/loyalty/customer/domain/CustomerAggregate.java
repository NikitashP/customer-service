package com.loyalty.customer.domain;

import com.loyalty.customer.command.AddPointsCommand;
import com.loyalty.customer.command.DeductPointsCommand;
import com.loyalty.customer.command.InitializeCustomerCommand;
import com.loyalty.customer.event.AddPointsEvent;
import com.loyalty.customer.event.DeductPointsEvent;
import com.loyalty.customer.event.InitializeCustomerEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class CustomerAggregate {

    private static final long serialVersionUID = -5977984483620451665L;

    @AggregateIdentifier
    private String id;

    private long bonusPoints;

    CustomerAggregate(){

    }

    @CommandHandler
    public CustomerAggregate(InitializeCustomerCommand initializeCustomerCommand){
    apply(new InitializeCustomerEvent(initializeCustomerCommand.getId(),initializeCustomerCommand.getPoints()));
    }

    @EventSourcingHandler
    void on(InitializeCustomerEvent initializeCustomerEvent){
        this.id=initializeCustomerEvent.getId();
        this.bonusPoints=initializeCustomerEvent.getPoints();
    }

    @CommandHandler
    void handle(DeductPointsCommand deductPointsCommand){

        if(deductPointsCommand.getPoints() > bonusPoints){
            throw new IllegalArgumentException("Insufficient Bonus points");
        }

    apply(new DeductPointsEvent(deductPointsCommand.getId(),deductPointsCommand.getPoints()));
    }

    @EventSourcingHandler
    void on(DeductPointsEvent deductPointsEvent){
        this.bonusPoints-=deductPointsEvent.getPoints();
    }

    @CommandHandler
    void handle(AddPointsCommand deductPointsCommand){
        apply(new AddPointsEvent(deductPointsCommand.getId(),deductPointsCommand.getPoints()));
    }

    @EventSourcingHandler
    void on(AddPointsEvent addPointsEvent){
        this.bonusPoints+=addPointsEvent.getPoints();
    }

}
