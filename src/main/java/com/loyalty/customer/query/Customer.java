package com.loyalty.customer.query;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Customer {

    @Id
    private String id;

    @Setter
    private long points;


    public Customer(String id, long points) {
        this.id = id;
        this.points = points;
    }

}
