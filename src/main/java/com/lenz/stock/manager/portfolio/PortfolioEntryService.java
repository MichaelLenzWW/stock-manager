package com.lenz.stock.manager.portfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Michael Lenz
 *
 */
@Service
public class PortfolioEntryService {

  @Autowired
  private PortfolioEntryRepository repository;

  /**
   * 
   */
  public PortfolioEntryService() {}

  /**
   * @param id
   * @return
   */
  public PortfolioEntry getById(final Long id) {
    return repository.findOne(id);
  }

  /**
   * Adds a {@link PortfolioEntry}.
   * 
   * @param portfolioEntry
   *          the {@link PortfolioEntry} to be added
   * 
   * @return the added {@link PortfolioEntry}
   */
  public PortfolioEntry add(final PortfolioEntry portfolioEntry) {

    // No portfolio entry ==> Nothing to add
    if (Objects.isNull(portfolioEntry)) {
      return null;
    }

    return repository.save(portfolioEntry);
  }

  /**
   * Updates a {@link PortfolioEntry}.
   * 
   * @param portfolioEntry
   *          the {@link PortfolioEntry} to be updated
   * 
   * @return the updated {@link PortfolioEntry}
   */
  public PortfolioEntry update(final PortfolioEntry portfolioEntry) {

    // No portfolio ==> Nothing to add
    if (Objects.isNull(portfolioEntry)) {
      return null;
    }

    return repository.save(portfolioEntry);
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
   * Fetch all portfolio entries from the database.
   * 
   * @return the {@link List} of {@link PortfolioEntry} fetched from the database
   */
  public List<PortfolioEntry> findAll() {

    List<PortfolioEntry> portfolioEntries = new ArrayList<>();
    repository.findAll().forEach(portfolioEntries::add);

    return portfolioEntries;
  }
}
