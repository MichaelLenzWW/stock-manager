package com.lenz.stock.manager.stock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lenz.stock.manager.persistence.SearchCriteria;
import com.lenz.stock.manager.persistence.StockSpecification;
import com.lenz.stock.manager.stock.Stock;
import com.lenz.stock.manager.stock.StockRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Transactional
public class JPASpecificationsTest {

  @Autowired
  private StockRepository stockRepository;

  private Stock mcd;
  private Stock ge;

  @Before
  public void init() {
    mcd = new Stock();
    mcd.setId(1l);
    mcd.setSymbol("MCD");
    mcd.setName("Mc Donalds");
    stockRepository.save(mcd);

    ge = new Stock();
    ge.setId(2l);
    ge.setSymbol("GE");
    ge.setName("General Electrics");

    stockRepository.save(ge);
  }

  @Test
  public void testSomething() {

    StockSpecification spec = new StockSpecification(new SearchCriteria("name", ":", "Mc Donalds"));

    Stock result = stockRepository.getOne(1l);
    Assert.assertEquals("Name is incorrect.", "Mc Donalds", result.getName());
  }
}