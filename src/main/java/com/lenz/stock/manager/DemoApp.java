package com.lenz.stock.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lenz.stock.manager.common.OptionType;
import com.lenz.stock.manager.order.Order;
import com.lenz.stock.manager.order.OrderRepository;
import com.lenz.stock.manager.order.OrderStatus;
import com.lenz.stock.manager.order.OrderType;
import com.lenz.stock.manager.stock.Stock;
import com.lenz.stock.manager.stock.StockRepository;

/**
 * Spring Boot Demo Application.
 * 
 * @author Michael Lenz
 *
 */
@SpringBootApplication
public class DemoApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Bootstrap Spring Boot
		SpringApplication.run(DemoApp.class, args);
	}

	@Bean
	protected ApplicationRunner initStocks(final StockRepository repository) {
		// return args -> System.out.println("Nothing to do here...");

		// Add the initial stocks for testing
		return args -> {
			List<Stock> stocks = new ArrayList<>();

			Stock pg = new Stock(1l, "1", "PG", "Procter & Gamble");
			pg.setValue(1200.35);

			Stock intc = new Stock(2l, "1", "INTC", "Intel Corporation");
			intc.setValue(-524.33);

			Stock macys = new Stock(3l, "1", "M", "Macys");
			macys.setValue(463.24);

			stocks.add(pg);
			stocks.add(intc);
			stocks.add(macys);
			stocks.forEach(stock -> repository.save(stock));
		};
	}

	@Bean
	protected ApplicationRunner initPortfolioEntries(final OrderRepository repository) {
		// return args -> System.out.println("Nothing to do here...");
		return args -> {

			// Add the initial portfolio entries for testing
			List<Order> orders = new ArrayList<>();

			orders.add(createMacysPut(1l, 3l));
			orders.add(createPGStock01(2l,1l));
			orders.add(createPGCall01(3l, 1l));	
			orders.add(createPGCall02(4l, 1l));

			orders.forEach(entry -> repository.save(entry));
		};
	}

	private Order createPGStock01(final Long id, final Long stockId) {
		Order order = new Order("1", id, stockId);
		order.setType(OrderType.SHARE);
		order.setQuantity(100.00);
		order.setCurrency("USD");
		order.setPurchasePrice(73.59);
		order.setPurchaseProvision(1.00);
		order.setSellPrice(75.50);
		order.setSellProvision(0.11);

		Calendar cal = new GregorianCalendar();
		cal.set(2018, 4, 14);
		order.setPurchaseDate(cal.getTime());

		cal.set(2018, 5, 22);
		order.setSellDate(cal.getTime());

		order.setStatus(OrderStatus.ASSIGNED);
		
		return order;
	}

	private Order createMacysPut(final Long id, final Long stockId) {
		Order order = new Order("1", id, stockId);
		order.setType(OrderType.STOCK_OPTION);
		order.setQuantity(-1.00);
		order.setCurrency("USD");
		order.setPurchasePrice(0.33);
		order.setPurchaseProvision(0.79);
		order.setSellProvision(0.00);

		Calendar cal = new GregorianCalendar();
		cal.set(2018, 4, 21);
		order.setPurchaseDate(cal.getTime());

		cal.set(2018, 4, 25);
		order.setSellDate(cal.getTime());

		order.setStatus(OrderStatus.EXPIRED);
		order.setOptionType(OptionType.PUT);
		order.setStrikePrice(38.50);

		cal.set(2018, 4, 25);
		order.setStrikeDate(cal.getTime());
		return order;
	}

	private Order createPGCall01(final Long id, final Long stockId) {
		Order order = new Order("1", id, stockId);
		order.setType(OrderType.STOCK_OPTION);
		order.setQuantity(-1.00);
		order.setCurrency("USD");
		order.setPurchasePrice(0.16);
		order.setPurchaseProvision(0.79);
		order.setSellPrice(0.0);
		order.setSellProvision(0.00);

		Calendar cal = new GregorianCalendar();
		cal.set(2018, 7, 02);
		order.setPurchaseDate(cal.getTime());

		cal.set(2018, 7, 03);
		order.setSellDate(cal.getTime());

		order.setStatus(OrderStatus.EXPIRED);
		order.setOptionType(OptionType.PUT);
		order.setStrikePrice(74.00);

		cal.set(2018, 3, 07);
		order.setStrikeDate(cal.getTime());
		return order;
	}

	private Order createPGCall02(final Long id, final Long stockId) {
		Order order = new Order("1", id, stockId);
		order.setType(OrderType.STOCK_OPTION);
		order.setQuantity(-1.00);
		order.setCurrency("USD");
		order.setPurchasePrice(0.18);
		order.setPurchaseProvision(1.09);
		order.setSellPrice(0.0);
		order.setSellProvision(0.00);

		Calendar cal = new GregorianCalendar();
		cal.set(2018, 4, 21);
		order.setPurchaseDate(cal.getTime());

		cal.set(2018, 4, 25);
		order.setSellDate(cal.getTime());

		order.setStatus(OrderStatus.EXPIRED);
		order.setOptionType(OptionType.CALL);
		order.setStrikePrice(75.00);

		cal.set(2018, 4, 25);
		order.setStrikeDate(cal.getTime());
		return order;
	}

}
