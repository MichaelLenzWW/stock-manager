package com.lenz.stock.manager.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Spring Data JPA Repository for the {@link Order}.
 * 
 * @author Michael Lenz
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    List<Order> findByStockId(final Long stockId);

}
