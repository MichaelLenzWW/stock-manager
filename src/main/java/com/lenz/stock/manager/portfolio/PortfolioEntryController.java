package com.lenz.stock.manager.portfolio;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PortfolioEntryController {

    private static final String PATH = "/portfolio";

    @Autowired
    private PortfolioEntryService service;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Get a {@link List} of all existing {@link PortfolioEntry}.
     * 
     * @param request
     * 
     * @param response
     * 
     * @return the {@link List} of {@link PortfolioEntry}
     */
    @GetMapping(PATH)
    public List<PortfolioEntry> getAllPortfolioEntries(final HttpServletRequest request, final HttpServletResponse response) {
	log.info("Receiving portfolio entries from the database.");
	List<PortfolioEntry> portfolioEntries = service.findAll();
	log.info(String.format("Received '%d' portfolio entries from the database.", portfolioEntries.size()));
	return portfolioEntries;
    }

    /**
     * Gets the {@link PortfolioEntry} for the given id.
     * 
     * @param request
     * 
     * @param response
     * 
     * @param id
     *            the id of the {@link PortfolioEntry} to be returned
     * 
     * @return The {@link PortfolioEntry} for the given id.<br/>
     *         Will return NULL, if no portfolio entry is found for the given id
     */
    @GetMapping(PATH + "/{id}")
    public PortfolioEntry getPortfolioEntry(final HttpServletRequest request, final HttpServletResponse response, @PathVariable final Long id) {
	return service.getById(id);
    }

    /**
     * Updates a {@link PortfolioEntry}.
     * 
     * @param request
     * 
     * @param response
     * 
     * @param portfolioEntry
     *            the {@link PortfolioEntry} to be updated
     * 
     * @return the added {@link PortfolioEntry}
     */
    @PutMapping(PATH)
    public PortfolioEntry updatePortfolioEntry(final HttpServletRequest request, final HttpServletResponse response,
	@RequestBody final PortfolioEntry portfolioEntry) {
	return service.update(portfolioEntry);
    }

    /**
     * Adds a {@link PortfolioEntry}.
     * 
     * @param request
     * 
     * @param response
     * 
     * @param portfolioEntry
     *            the {@link PortfolioEntry} to be added
     * 
     * @return the added {@link PortfolioEntry}
     */
    @PostMapping(PATH)
    public PortfolioEntry addPortfolioEntry(final HttpServletRequest request, final HttpServletResponse response, @RequestBody final PortfolioEntry portfolioEntry) {
	try {
	    return service.add(portfolioEntry);
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
     * Deletes a {@link PortfolioEntry}.
     * 
     * @param request
     * 
     * @param response
     * 
     * @param id
     *            the id of the {@link PortfolioEntry} to be deleted
     */
    @DeleteMapping(PATH + "/{id}")
    public void deletePortfolioEntry(final HttpServletRequest request, final HttpServletResponse response, @PathVariable final Long id) {
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
     *            the {@link PortfolioEntryService} to set
     */
    public void setPortfolioEntryService(final PortfolioEntryService service) {
	this.service = service;
    }
}
