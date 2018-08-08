package com.lenz.stock.manager.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.lenz.stock.manager.persistence.SearchCriteria;

public class OrderSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public OrderSpecificationsBuilder() {
	params = new ArrayList<SearchCriteria>();
    }

    public OrderSpecificationsBuilder with(final String key, final String operation, final Object value) {
	params.add(new SearchCriteria(key, operation, value));
	return this;
    }

    public Specification<Order> build() {
	if (params.size() == 0) {
	    return null;
	}

	List<Specification<Order>> specs = new ArrayList<Specification<Order>>();
	for (SearchCriteria param : params) {
	    specs.add(new OrderSpecification(param));
	}

	Specification<Order> result = specs.get(0);
	for (int i = 1; i < specs.size(); i++) {
	    result = Specifications.where(result).and(specs.get(i));
	}
	return result;
    }
}