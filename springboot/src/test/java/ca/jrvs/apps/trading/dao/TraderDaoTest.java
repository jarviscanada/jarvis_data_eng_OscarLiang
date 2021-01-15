package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderDaoTest {

  @Autowired
  private TraderDao traderDao;

  private Trader savedTrader;

  @Before
  public void setUp() throws Exception {
    savedTrader = new Trader();
    savedTrader.setFirstName("Bob");
    savedTrader.setLastName("Jones");
    savedTrader.setCountry("Canada");
    savedTrader.setDob(new Date(System.currentTimeMillis()));
    savedTrader.setEmail("bobjones@yahoo.com");
    traderDao.save(savedTrader);
  }

  @After
  public void tearDown() throws Exception {
    traderDao.deleteAll();
  }

  @Test
  public void findAllById() {
    List<Trader> traders = Lists
        .newArrayList(traderDao.findAllById(Arrays.asList(savedTrader.getId(), -1)));
    assertEquals(1, traders.size());
    assertEquals(savedTrader.getCountry(), traders.get(0).getCountry());
  }

  @Test
  public void existsById() {
    assertTrue(traderDao.existsById(savedTrader.getId()));
    assertFalse(traderDao.existsById(-1));
  }

  @Test
  public void findAll() {
    List<Trader> traders = traderDao.findAll();
    assertEquals(1, traders.size());
    assertEquals(savedTrader.getEmail(), traders.get(0).getEmail());
  }

  @Test
  public void count() {
    assertEquals(1, traderDao.count());
  }
}