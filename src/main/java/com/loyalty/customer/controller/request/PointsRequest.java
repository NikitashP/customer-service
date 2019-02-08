package com.loyalty.customer.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PointsRequest {
    @NotNull
    private String id;
    @NotNull
    private long points;
}
