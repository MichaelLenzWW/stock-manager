package com.lenz.stock.manager.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author Michael Lenz
 *
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    /**
     * 
     */
    public OrderService() {
    }

    /**
     * @param id
     * @return
     */
    public Order getById(final Long id) {
	return repository.findOne(id);
    }

    /**
     * Adds a {@link Order}.
     * 
     * @param order
     *            the {@link Order} to be added
     * 
     * @return the added {@link Order}
     */
    public Order add(final Order order) {

	// No order ==> Nothing to add
	if (Objects.isNull(order)) {
	    return null;
	}

	return repository.save(order);
    }

    /**
     * Updates a {@link Order}.
     * 
     * @param order
     *            the {@link Order} to be updated
     * 
     * @return the updated {@link Order}
     */
    public Order update(final Order order) {

	// No order ==> Nothing to add
	if (Objects.isNull(order)) {
	    return null;
	}

	return repository.save(order);
    }

    /**
     * @param id
     */
    public void deleteById(final Long id) {

	// No id ==> nothing to delete
	if (Objects.isNull(id)) {
	    return;
	}

	repository.delete(id);
    }

    /**
     * Fetch all orders from the database.
     * 
     * @return the {@link List} of {@link Order} fetched from the database
     */
    public List<Order> findAll() {

	List<Order> orders = new ArrayList<>();
	repository.findAll().forEach(orders::add);

	return orders;
    }

    /**
     * 
     * @param specification
     * @return
     */
    public List<Order> findAllBySpecification(final Specification<Order> specification) {
	return repository.findAll(specification);
    }
}
