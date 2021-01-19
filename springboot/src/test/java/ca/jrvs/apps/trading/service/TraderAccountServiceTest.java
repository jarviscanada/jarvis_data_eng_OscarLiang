package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import java.util.Date;
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
public class TraderAccountServiceTest {

  private TraderAccountView savedView;
  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  @Before
  public void setUp() {

    Trader trader = new Trader();
    trader.setFirstName("Bob");
    trader.setLastName("Jones");
    trader.setDob(new Date(System.currentTimeMillis()));
    trader.setCountry("Canada");
    trader.setEmail("bobjones@yahoo.com");
    savedView = traderAccountService.createTraderAndAccount(trader);
  }

  @After
  public void tearDown() {
    accountDao.deleteById(savedView.getAccount().getId());
    traderDao.deleteById(savedView.getTrader().getId());
  }

  @Test
  public void createTraderAndAccount() {

    Optional<Trader> testTrader = traderDao.findById(savedView.getTrader().getId());
    assertTrue(testTrader.isPresent());
    assertEquals(savedView.getTrader().getFirstName(), testTrader.get().getFirstName());
    Optional<Account> testAccount = accountDao.findById(savedView.getAccount().getId());
    assertTrue(testAccount.isPresent());

    try {
      traderAccountService.createTraderAndAccount(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteTraderById() {
    try {
      traderAccountService.deleteTraderById(savedView.getTrader().getId());
      assertTrue(true);
    } catch (Exception e) {
      fail();
    }

    try {
      traderAccountService.deleteTraderById(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deposit() {
    Double depositAmount = 1000.0;
    traderAccountService.deposit(savedView.getTrader().getId(), depositAmount);

    Optional<Account> account = accountDao.findById(savedView.getAccount().getId());
    assertTrue(account.isPresent());
    assertEquals(depositAmount, account.get().getAmount());
    try {
      traderAccountService.deposit(null, 100.0);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      traderAccountService.deposit(-1, 100.0);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      traderAccountService.deposit(savedView.getTrader().getId(), 0.0);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      traderAccountService.deposit(savedView.getTrader().getId(), -100.0);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void withdraw() {

    Double depositAmount = 1000.0;
    Double withdrawalAmount = 700.0;
    try {
      traderAccountService.withdraw(savedView.getTrader().getId(), withdrawalAmount);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    traderAccountService.deposit(savedView.getTrader().getId(), depositAmount);
    traderAccountService.withdraw(savedView.getTrader().getId(), withdrawalAmount);

    Optional<Account> account = accountDao.findById(savedView.getAccount().getId());
    assertTrue(account.isPresent());
    assertEquals((Double) (depositAmount - withdrawalAmount), account.get().getAmount());

    try {
      traderAccountService.withdraw(null, 100.0);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      traderAccountService.withdraw(-1, 100.0);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      traderAccountService.withdraw(savedView.getTrader().getId(), 0.0);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      traderAccountService.withdraw(savedView.getTrader().getId(), -100.0);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}