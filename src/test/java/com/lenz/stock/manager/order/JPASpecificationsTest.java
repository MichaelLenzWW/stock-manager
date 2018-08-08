package com.lenz.stock.manager.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lenz.stock.manager.common.OptionType;
import com.lenz.stock.manager.persistence.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Transactional
public class JPASpecificationsTest {

    @Autowired
    private OrderRepository orderRepository;

    private Order orderMacys;

    @Before
    public void init() {

	List<Order> orders = createOrders();
	orders.forEach(order -> orderRepository.save(order));
    }

    @Test
    public void testSomething() {

	// given
	OrderSpecification spec = new OrderSpecification(new SearchCriteria("stockId", ":", 3l));

	// when
	List<Order> result = orderRepository.findAll(spec);

	// then
	Assert.assertEquals("Order stock id is incorrect.", 1l, result.get(0).getId().longValue());
    }

    /**
     * 
     * @return a {@link List} of {@link Order}
     */
    private List<Order> createOrders() {

	List<Order> orders = new ArrayList<>();

	orderMacys = new Order("1", 1l, 3l);
	orderMacys.setType(OrderType.STOCK_OPTION);
	orderMacys.setQuantity(1.00);
	orderMacys.setCurrency("USD");
	orderMacys.setPrice(0.33);
	orderMacys.setProvisionPurchase(0.79);
	orderMacys.setProvisionSell(0.00);

	Calendar cal = new GregorianCalendar();
	cal.set(2018, 7, 2);
	orderMacys.setPurchaseDate(cal.getTime());

	cal.set(2018, 7, 3);
	orderMacys.setSellDate(cal.getTime());

	orderMacys.setStatus(OrderStatus.EXPIRED);
	orderMacys.setOptionType(OptionType.PUT);
	orderMacys.setStrikePrice(38.50);

	cal.set(2018, 7, 3);
	orderMacys.setStrikeDate(cal.getTime());
	orders.add(orderMacys);

	return orders;
    }
}