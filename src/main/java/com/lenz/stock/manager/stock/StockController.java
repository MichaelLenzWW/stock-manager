package com.lenz.stock.manager.stock;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Lenz
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class StockController {

	private static final String PATH = "/stock";

	@Autowired
	private StockService stockService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Get a {@link List} of all existing {@link Stock}.
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @return the {@link List} of {@link Stock}
	 */
	@GetMapping(PATH)
	public List<Stock> getAllStocks(final HttpServletRequest request, final HttpServletResponse response) {
		log.info("Receiving stocks from the database.");
		List<Stock> stocks = stockService.findAll();
		log.info(String.format("Received '%d' stocks from the database.", stocks.size()));
		return stocks;
	}

	/**
	 * Gets the {@link Stock} for the given id.
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @param id
	 *            the id of the {@link Stock} to be returned
	 * 
	 * @return The {@link Stock} for the given id.<br/>
	 *         Will return NULL, if no stock is found for the given id
	 */
	@GetMapping(PATH + "/{id}")
	public Stock getStock(final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable final Long id) {
		return stockService.getById(id);
	}

	/**
	 * Updates a {@link Stock}.
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @param stock
	 *            the {@link Stock} to be updated
	 * 
	 * @return the added {@link Stock}
	 */
	@PutMapping(PATH)
	public Stock updateStock(final HttpServletRequest request, final HttpServletResponse response,
			@RequestBody final Stock stock) {
		return stockService.update(stock);
	}

	/**
	 * Adds a {@link Stock}.
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @param stock
	 *            the {@link Stock} to be added
	 * 
	 * @return the added {@link Stock}
	 */
	@PostMapping(PATH)
	public Stock addStock(final HttpServletRequest request, final HttpServletResponse response,
			@RequestBody final Stock stock) {
		try {
			return stockService.add(stock);
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
	 * Deletes a {@link Stock}.
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @param id
	 *            the id of the {@link Stock} to be deleted
	 */
	@DeleteMapping(PATH + "/{id}")
	public void deleteStock(final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable final Long id) {
		try {
			stockService.deleteById(id);
		} catch (Exception exception) {
			try {
				response.sendError(HttpServletResponse.SC_CONFLICT, exception.getMessage());
			} catch (IOException e) {
				// Nothing to do here
			}
		}
	}

	/**
	 * Setter for the stockService.
	 * 
	 * @param stockService
	 *            the stockService to set
	 */
	public void setStockService(final StockService stockService) {
		this.stockService = stockService;
	}
}
