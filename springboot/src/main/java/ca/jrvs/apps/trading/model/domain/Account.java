package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Account implements Entity<Integer> {

  private Integer id;
  private Integer traderId;
  private Double amount;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer integer) {
    id = integer;
  }

  public Integer getTraderId() {
    return traderId;
  }

  public void setTraderId(Integer traderId) {
    this.traderId = traderId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(id, account.id) &&
        Objects.equals(traderId, account.traderId) &&
        Objects.equals(amount, account.amount);
  }
}
