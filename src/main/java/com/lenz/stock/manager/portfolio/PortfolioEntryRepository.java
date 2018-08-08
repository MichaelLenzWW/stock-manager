package com.lenz.stock.manager.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Spring Data JPA Repository for the {@link PortfolioEntry}.
 * 
 * @author Michael Lenz
 *
 */
public interface PortfolioEntryRepository extends JpaRepository<PortfolioEntry, Long>, JpaSpecificationExecutor<PortfolioEntry> {
}
