package com.lenz.stock.manager.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Spring Data JPA Repository for the {@link Stock}.
 * 
 * @author Michael Lenz
 *
 */
public interface StockRepository extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock> {}
