package com.lenz.stock.manager.order;

public enum OrderStatus {

    PURCHASED("PURCHASED", "Purchased"),
    SOLD("SOLD", "Sold"),
    ROLLED("ROLLED", "Rolled"),
    EXPIRED("EXPIRED", "Expired"),
    ASSIGNED("ASSIGNED", "Assigned");

    private String name;

    private String description;

    private OrderStatus(final String name, final String description) {
	this.name = name;
	this.description = description;
    }

    public String getName() {
	return name;
    }

    public String getDescription() {
	return description;
    }

}
