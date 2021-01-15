package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.ArrayList;
import java.util.Arrays;
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
public class AccountDaoIntTest {

  @Autowired
  AccountDao accountDao;

  @Autowired
  TraderDao traderDao;

  private Account savedAccount;
  private Trader savedTrader;

  @Before
  public void setUp() {
    savedTrader = new Trader();
    savedTrader.setFirstName("Bob");
    savedTrader.setLastName("Jones");
    savedTrader.setDob(new Date(System.currentTimeMillis()));
    savedTrader.setCountry("Canada");
    savedTrader.setEmail("bobjones@yahoo.com");
    traderDao.save(savedTrader);

    savedAccount = new Account();
    savedAccount.setAmount(250.1d);
    savedAccount.setTraderId(savedTrader.getId());
    accountDao.save(savedAccount);
  }

  @After
  public void tearDown() {
    accountDao.deleteAll();
    traderDao.deleteById(savedTrader.getId());
  }

  @Test
  public void updateOne() {
    savedAccount.setAmount(2000d);
    accountDao.updateOne(savedAccount);
    Optional<Account> resultAccount = accountDao.findById(savedAccount.getId());
    assertTrue(resultAccount.isPresent());
    assertEquals(savedAccount.getAmount(), resultAccount.get().getAmount());
  }

  @Test
  public void saveAll() {
    List<Account> savedAccounts = new ArrayList<>();

    Account account1 = new Account();
    account1.setAmount(12.4d);
    account1.setTraderId(savedTrader.getId());
    savedAccounts.add(account1);

    Account account2 = new Account();
    account2.setAmount(523.4d);
    account2.setTraderId(savedTrader.getId());
    savedAccounts.add(account2);

    accountDao.saveAll(savedAccounts);
    List<Account> resultAccounts = accountDao.findAll();

    assertEquals(3, resultAccounts.size());
  }

  @Test
  public void findAllById() {
    List<Account> accounts = new ArrayList(accountDao.findAllById(Arrays.asList(savedAccount.
        getId())));
    assertEquals(1, accounts.size());
    assertEquals(savedAccount.getAmount(), accounts.get(0).getAmount());
  }

}