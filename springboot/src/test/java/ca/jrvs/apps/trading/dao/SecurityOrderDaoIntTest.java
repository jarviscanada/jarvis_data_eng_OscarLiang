package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
public class SecurityOrderDaoIntTest {

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
    savedSecurityOrder.setStatus("Status");
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
  public void updateOne() {
    savedSecurityOrder.setSize(20);
    securityOrderDao.updateOne(savedSecurityOrder);
    Optional<SecurityOrder> result = securityOrderDao.findById(savedSecurityOrder.getId());
    assertTrue(result.isPresent());
    assertEquals(savedSecurityOrder.getSize(), result.get().getSize());
  }

  @Test
  public void saveAll() {
    List<SecurityOrder> testList = new ArrayList<>();
    testList.add(new SecurityOrder());
    testList.get(0).setAccountId(savedAccount.getId());
    testList.get(0).setStatus("Waiting");
    testList.get(0).setTicker(savedQuote.getTicker());
    testList.get(0).setSize(200);
    testList.get(0).setPrice(15.2);
    testList.get(0).setNotes("These are notes");

    testList.add(new SecurityOrder());
    testList.get(1).setAccountId(savedAccount.getId());
    testList.get(1).setStatus("Complete");
    testList.get(1).setTicker(savedQuote.getTicker());
    testList.get(1).setSize(16);
    testList.get(1).setPrice(927.1);
    testList.get(1).setNotes("Even more notes");
    securityOrderDao.saveAll(testList);
    assertEquals(3, securityOrderDao.count());
  }
}