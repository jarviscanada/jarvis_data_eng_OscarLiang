package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Date;
import java.util.List;
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
public class PositionDaoIntTest {

  @Autowired
  private PositionDao positionDao;

  @Autowired
  private SecurityOrderDao securityOrderDao;

  @Autowired
  private QuoteDao quoteDao;

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private TraderDao traderDao;

  private SecurityOrder savedSecurityOrder;
  private Quote savedQuote;
  private Account savedAccount;
  private Trader savedTrader;

  @Before
  public void setUp() {
    savedQuote = new Quote();
    savedQuote.setAskPrice(12d);
    savedQuote.setAskSize((long) 100);
    savedQuote.setBidPrice(4d);
    savedQuote.setBidSize((long) 200);
    savedQuote.setId("MSFT");
    savedQuote.setLastPrice(9.5d);
    quoteDao.save(savedQuote);

    savedTrader = new Trader();
    savedTrader.setFirstName("Bob");
    savedTrader.setLastName("Jones");
    savedTrader.setDob(new Date(System.currentTimeMillis()));
    savedTrader.setCountry("Canada");
    savedTrader.setEmail("bobjones@yahoo.com");
    traderDao.save(savedTrader);

    savedAccount = new Account();
    savedAccount.setTraderId(savedTrader.getId());
    savedAccount.setAmount(30.0);
    savedAccount = accountDao.save(savedAccount);

    savedSecurityOrder = new SecurityOrder();
    savedSecurityOrder.setAccountId(savedAccount.getId());
    savedSecurityOrder.setStatus("FILLED");
    savedSecurityOrder.setTicker(savedQuote.getTicker());
    savedSecurityOrder.setSize(10);
    savedSecurityOrder.setPrice(20d);
    savedSecurityOrder.setNotes("Note");
    savedSecurityOrder = securityOrderDao.save(savedSecurityOrder);
  }

  @After
  public void tearDown() {
    securityOrderDao.deleteAll();
    quoteDao.deleteById(savedQuote.getTicker());
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
  }

  @Test
  public void findById() {
    List<Position> positions = positionDao.findById(savedSecurityOrder.getId());
    assertEquals(1, positions.size());
    assertEquals(savedSecurityOrder.getSize(), positions.get(0).getPosition());
  }

  @Test
  public void findAll() {
    List<Position> testList = positionDao.findAll();
    assertEquals(savedSecurityOrder.getSize(), testList.get(0).getPosition());
  }

  @Test
  public void count() {
    assertEquals(1, positionDao.count());
  }

}