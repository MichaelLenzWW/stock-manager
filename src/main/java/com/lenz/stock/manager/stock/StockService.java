package com.lenz.stock.manager.stock;

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
public class StockService {

  @Autowired
  private StockRepository stockRepository;

  /**
   * 
   */
  public StockService() {}

  /**
   * @param id
   * @return
   */
  public Stock getById(final Long id) {
    return stockRepository.findOne(id);
  }

  /**
   * Adds a {@link Stock}.
   * 
   * @param stock
   *          the {@link Stock} to be added
   * 
   * @return the added {@link Stock}
   */
  public Stock add(final Stock stock) {

    // No stock ==> Nothing to add
    if (Objects.isNull(stock)) {
      return null;
    }

    return stockRepository.save(stock);
  }

  /**
   * Updates a {@link Stock}.
   * 
   * @param stock
   *          the {@link Stock} to be updated
   * 
   * @return the updated {@link Stock}
   */
  public Stock update(final Stock stock) {

    // No stock ==> Nothing to add
    if (Objects.isNull(stock)) {
      return null;
    }

    return stockRepository.save(stock);
  }

  /**
   * @param id
   */
  public void deleteById(final Long id) {

    // No id ==> nothing to delete
    if (Objects.isNull(id)) {
      return;
    }

    stockRepository.delete(id);
  }

  /**
   * Fetch all stocks from the database.
   * 
   * @return the {@link List} of {@link Stock} fetched from the database
   */
  public List<Stock> findAll() {

    List<Stock> stocks = new ArrayList<>();
    stockRepository.findAll().forEach(stocks::add);

    return stocks;
  }
}
