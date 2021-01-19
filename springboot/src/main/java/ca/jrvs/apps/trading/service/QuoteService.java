package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private final QuoteDao quoteDao;
  private final MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }


  /**
   * Update quote table against IEX source - get all quotes from the db - foreach ticker get
   * iexQuote - convert iexQuote to quote entity - persist quote to db
   *
   * @throws org.springframework.dao.DataAccessException if unable to retrieve data
   * @throws IllegalArgumentException                    for invalid input
   */
  public void updateMarketData() {
    List<Quote> quotes = quoteDao.findAll();
    quotes.forEach(quote -> {
      IexQuote iexQuote = marketDataDao.findById(quote.getId()).get();
      quoteDao.save(buildQuoteFromIexQuote(iexQuote));
    });
  }

  /**
   * Helper method. Map a IexQuote to a Quote entity. Note: `iexQuote.getLatestPrice() == null` if
   * the stock market is closed. Make sure to set a default value for number field(s).
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    Quote quote = new Quote();
    quote.setTicker(iexQuote.getSymbol());

    Double askPrice = iexQuote.getIexAskPrice();
    quote.setAskPrice((askPrice != null) ? askPrice : 0);

    Long askSize = iexQuote.getIexAskSize();
    quote.setAskSize((askSize != null) ? askSize : 0);

    Double lastPrice = iexQuote.getLatestPrice();
    quote.setLastPrice((lastPrice != null) ? lastPrice : 0);

    Double bidPrice = iexQuote.getIexBidPrice();
    quote.setBidPrice((bidPrice != null) ? bidPrice : 0);

    Long bidSize = iexQuote.getIexBidSize();
    quote.setBidSize((bidSize != null) ? bidSize : 0);

    return quote;
  }

  /**
   * Validate (against IEX) and save given tickers to quote table.
   * <p>
   * - Get iexQuote(s) - convert each iexQuote to Quote entity - persist the quote to db
   *
   * @param tickers a list of tickers/symbols
   * @throws IllegalArgumentException if ticker is not found from IEX
   */
  public List<Quote> saveQuotes(List<String> tickers) {
    List<Quote> quoteList = new ArrayList<>();
    tickers.forEach(ticker -> quoteList.add(saveQuote(ticker)));
    return quoteList;
  }

  /**
   * Helper method
   */
  public Quote saveQuote(String ticker) {
    Quote quote = buildQuoteFromIexQuote(findIexQuoteByTicker(ticker));
    return saveQuote(quote);
  }

  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
  }

  /**
   * Update a given quote to quote table without validation
   *
   * @param quote entity
   */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  /**
   * Find all quotes from the quote table
   *
   * @return a list of quotes
   */
  public List<Quote> findAllQuotes() {
    return quoteDao.findAll();
  }
}
