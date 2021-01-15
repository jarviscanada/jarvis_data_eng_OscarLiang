package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
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
public class QuoteDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;

  private Quote savedQuote;

  @Before
  public void insertOne() {
    savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("AAPL");
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
  }

  @After
  public void deleteOne() {
    quoteDao.deleteById(savedQuote.getId());
  }

  @Test
  public void save() {
    Quote testQuote = new Quote();
    testQuote.setAskPrice(15d);
    testQuote.setAskSize(20);
    testQuote.setBidPrice(12d);
    testQuote.setBidSize(11);
    testQuote.setId("MSFT");
    testQuote.setLastPrice(13.1d);
    quoteDao.save(testQuote);
    Quote resultQuote = quoteDao.findById("MSFT").get();
    assertEquals(testQuote, resultQuote);
  }

  @Test
  public void testSaveAll() {
    Quote testQuote1 = new Quote();
    testQuote1.setAskPrice(15d);
    testQuote1.setAskSize(20);
    testQuote1.setBidPrice(12d);
    testQuote1.setBidSize(11);
    testQuote1.setId("MSFT");
    testQuote1.setLastPrice(13.1d);

    Quote testQuote2 = new Quote();
    testQuote2.setAskPrice(15d);
    testQuote2.setAskSize(20);
    testQuote2.setBidPrice(12d);
    testQuote2.setBidSize(11);
    testQuote2.setId("FB");
    testQuote2.setLastPrice(13.1d);
    quoteDao.saveAll(Arrays.asList(testQuote1, testQuote2));

    Quote resultQuote1 = quoteDao.findById("MSFT").get();
    Quote resultQuote2 = quoteDao.findById("FB").get();
    assertEquals(testQuote1, resultQuote1);
    assertEquals(testQuote2, resultQuote2);
  }

  @Test
  public void findByIdTest() {
    assertEquals(savedQuote, quoteDao.findById(savedQuote.getId()).get());
  }

  @Test
  public void existsByIdTest() {
    assertTrue(quoteDao.existsById(savedQuote.getId()));
  }

  @Test
  public void findAllTest() {
    List<Quote> result = quoteDao.findAll();
    assertEquals(1, result.size());
    assertEquals(savedQuote, result.get(0));
  }

  @Test
  public void countTest() {
    assertEquals(1, quoteDao.count());
  }

  @Test
  public void deleteByIdTest() {
    quoteDao.deleteById(savedQuote.getId());
    assertEquals(0, quoteDao.count());
  }

  @Test
  public void deleteAllTest() {
    quoteDao.deleteAll();
    assertEquals(0, quoteDao.count());
  }
}