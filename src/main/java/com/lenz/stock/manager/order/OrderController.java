package com.lenz.stock.manager.order;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Lenz
 *
 */
@RestController
public class OrderController {

    private static final String PATH = "/orders";

    @Autowired
    private OrderService service;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Get a {@link List} of all existing {@link Order}.
     * 
     * @param request
     * 
     * @param response
     * 
     * @return the {@link List} of {@link Order}
     */
    // @GetMapping(PATH)
    // public List<Order> getAllOrders(final HttpServletRequest request, final
    // HttpServletResponse response) {
    // log.info("Fetching orders from the database.");
    // List<Order> orders = service.findAll();
    // log.info(String.format("Fetched '%d' orde rs from the database.",
    // orders.size()));
    // return orders;
    // }

    @GetMapping(PATH)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<Order> getAllOrders(final HttpServletRequest request, final HttpServletResponse response, @RequestParam(value = "search") String search) {

	log.info("Fetching orders from the database.");

	OrderSpecificationsBuilder builder = new OrderSpecificationsBuilder();

	Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
	Matcher matcher = pattern.matcher(search + ",");
	while (matcher.find()) {
	    builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	}

	Specification<Order> specification = builder.build();
	List<Order> orders = service.findAllBySpecification(specification);

	log.info(String.format("Fetched '%d' orders from the database.", orders.size()));
	return orders;
    }

    /**
     * Gets the {@link Order} for the given id.
     * 
     * @param request
     * 
     * @param response
     * 
     * @param id
     *            the id of the {@link Order} to be returned
     * 
     * @return The {@link Order} for the given id.<br/>
     *         Will return NULL, if no order is found for the given id
     */
    @GetMapping(PATH + "/{id}")
    public Order getOrder(final HttpServletRequest request, final HttpServletResponse response, @PathVariable final Long id) {
	return service.getById(id);
    }

    /**
     * Updates a {@link Order}.
     * 
     * @param request
     * 
     * @param response
     * 
     * @param order
     *            the {@link Order} to be updated
     * 
     * @return the added {@link Order}
     */
    @PutMapping(PATH)
    public Order updateOrder(final HttpServletRequest request, final HttpServletResponse response,
	@RequestBody final Order order) {
	return service.update(order);
    }

    /**
     * Adds a {@link Order}.
     * 
     * @param request
     * 
     * @param response
     * 
     * @param order
     *            the {@link Order} to be added
     * 
     * @return the added {@link Order}
     */
    @PostMapping(PATH)
    public Order addOrder(final HttpServletRequest request, final HttpServletResponse response, @RequestBody final Order order) {
	try {
	    return service.add(order);
	} catch (Exception exception) {
	    try {
		response.sendError(HttpServletResponse.SC_CONFLICT, exception.getMessage());
	    } catch (IOException e) {
		// Nothing to do here
	    }
	    return null;
	}
    }

    /**
     * Deletes a {@link Order}.
     * 
     * @param request
     * 
     * @param response
     * 
     * @param id
     *            the id of the {@link Order} to be deleted
     */
    @DeleteMapping(PATH + "/{id}")
    public void deleteOrder(final HttpServletRequest request, final HttpServletResponse response, @PathVariable final Long id) {
	try {
	    service.deleteById(id);
	} catch (Exception exception) {
	    try {
		response.sendError(HttpServletResponse.SC_CONFLICT, exception.getMessage());
	    } catch (IOException e) {
		// Nothing to do here
	    }
	}
    }

    /**
     * Setter for the service.
     * 
     * @param service
     *            the {@link OrderService} to set
     */
    public void setOrderService(final OrderService service) {
	this.service = service;
    }
}
