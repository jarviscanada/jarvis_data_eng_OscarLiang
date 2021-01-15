package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  @Override
  public Quote save(Quote quote) {
    if (existsById(quote.getTicker())) {
      int updateRowNo = updateOne(quote);
      if (updateRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * helper method that saves one quote
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  /**
   * helper method that updates one quote
   */
  private int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, "
        + "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * hepler method that makes sql update values objects
   *
   * @param quote to be updated
   * @return UPDATE_SQL values
   */
  private Object[] makeUpdateValues(Quote quote) {
    return new Object[]{quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(),
        quote.getAskPrice(), quote.getAskSize(), quote.getTicker()};
  }

  /**
   * Saves all given entities.
   *
   * @param entities must not be {@literal null}.
   * @return the saved entities will never be {@literal null}.
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public <S extends Quote> Iterable<S> saveAll(Iterable<S> entities) {
    for (S entity : entities) {
      save(entity);
    }
    return entities;
  }

  /**
   * Retrieves an entity by its id.
   *
   * @param s must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public Optional<Quote> findById(String s) {
    Optional<Quote> quote = null;
    try {
      quote = Optional.of(findById(s, false));
    } catch (DataRetrievalFailureException e) {
      quote = Optional.empty();
    }
    return quote;
  }

  private Quote findById(String id, boolean forUpdate) {
    Quote quote = null;
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
    if (forUpdate) {
      selectSql += " for update";
    }
    try {
      quote = jdbcTemplate.queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(Quote.class), id);
    } catch (EmptyResultDataAccessException e) {
      logger.debug("Can't find ticker symbol:" + id, e);
    }
    if (quote == null) {
      throw new DataRetrievalFailureException("Resource not found");
    }
    return quote;
  }

  /**
   * Returns whether an entity with the given id exists.
   *
   * @param s must not be {@literal null}.
   * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public boolean existsById(String s) {
    return findById(s).isPresent();
  }

  /**
   * Returns all instances of the type.
   *
   * @return all entities
   */
  @Override
  public List<Quote> findAll() {
    String selectSql = "SELECT * FROM " + TABLE_NAME;
    return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Quote.class));

  }

  /**
   * Returns all instances of the type with the given IDs.
   *
   * @param strings
   * @return
   */
  @Override
  public Iterable<Quote> findAllById(Iterable<String> strings) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Returns the number of entities available.
   *
   * @return the number of entities
   */
  @Override
  public long count() {
    return findAll().size();
  }

  /**
   * Deletes the entity with the given id.
   *
   * @param ticker must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  @Override
  public void deleteById(String ticker) {
    String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
    jdbcTemplate.update(deleteSql, ticker);
  }

  /**
   * Deletes a given entity.
   *
   * @param entity
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public void delete(Quote entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes the given entities.
   *
   * @param entities
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends Quote> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes all entities managed by the repository.
   */
  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(deleteSql);
  }
}
