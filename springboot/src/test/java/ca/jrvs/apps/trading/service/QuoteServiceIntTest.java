package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import java.util.List;
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
public class QuoteServiceIntTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setUp() throws Exception {
    quoteDao.deleteAll();
  }

  @Test
  public void updateMarketData() {
    Quote quote = new Quote();
    quote.setId("MSFT");
    quote.setAskPrice(-100d);
    quote.setAskSize((long) 100);
    quote.setBidPrice(-23d);
    quote.setBidSize((long) 100);
    quote.setLastPrice(25.1d);
    quoteService.saveQuote(quote);
    quoteService.updateMarketData();
    Quote resultQuote = quoteDao.findById(quote.getId()).get();
    assertNotEquals(quote, resultQuote);
  }

  @Test
  public void saveQuotes() {
    List<Quote> quotes = quoteService.saveQuotes(Arrays.asList("AAPL", "MSFT", "FB"));
    assertEquals(3, quotes.size());
  }

  @Test
  public void saveQuote() {
    String ticker = "MSFT";
    quoteService.saveQuote(ticker);
    assertTrue(quoteDao.findById(ticker).isPresent());
  }

  @Test
  public void findIexQuotebyTicker() {
    IexQuote iexQuote = quoteService.findIexQuoteByTicker("FB");
    assertEquals("FB", iexQuote.getSymbol());
    try {
      iexQuote = quoteService.findIexQuoteByTicker("FB2");
      fail();
    } catch (IllegalArgumentException ex) {
      assertTrue(true);
    }
  }

  @Test
  public void findAllQuotes() {
    Quote quote = new Quote();
    quote.setId("MSFT");
    quote.setAskPrice(35d);
    quote.setAskSize((long) 100);
    quote.setBidPrice(17.2d);
    quote.setBidSize((long) 100);
    quote.setLastPrice(25.1d);
    quoteService.saveQuote(quote);
    List<Quote> quoteList = quoteService.findAllQuotes();
    assertEquals(1, quoteList.size());
    assertEquals(quote, quoteList.get(0));
  }
}