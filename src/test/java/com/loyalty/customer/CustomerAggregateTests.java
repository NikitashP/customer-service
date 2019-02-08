package com.loyalty.customer;


import com.loyalty.customer.command.AddPointsCommand;
import com.loyalty.customer.command.DeductPointsCommand;
import com.loyalty.customer.command.InitializeCustomerCommand;
import com.loyalty.customer.domain.CustomerAggregate;
import com.loyalty.customer.event.AddPointsEvent;
import com.loyalty.customer.event.DeductPointsEvent;
import com.loyalty.customer.event.InitializeCustomerEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerAggregateTests {

	private FixtureConfiguration fixture;

	@BeforeEach
	public void setUp() {
		fixture = new AggregateTestFixture(CustomerAggregate.class);
	}

	@Test
	public void initializeCustomer() {
		fixture.given().when(new InitializeCustomerCommand("id",10L)).expectSuccessfulHandlerExecution().expectEvents(
				new InitializeCustomerEvent("id",10L));
	}

	@Test
	public void AddPointsToCustomer() {
		fixture.given(new InitializeCustomerEvent("id",10L))
				.when(new AddPointsCommand("id",10L)).expectSuccessfulHandlerExecution().expectSuccessfulHandlerExecution().expectEvents(
				new AddPointsEvent("id",10L));
	}

	@Test
	public void DeductPointsToCustomer() {
		fixture.given(new InitializeCustomerEvent("id",10L))
				.when(new DeductPointsCommand("id",10L)).expectSuccessfulHandlerExecution().expectSuccessfulHandlerExecution().expectEvents(
				new DeductPointsEvent("id",10L));
	}

}

