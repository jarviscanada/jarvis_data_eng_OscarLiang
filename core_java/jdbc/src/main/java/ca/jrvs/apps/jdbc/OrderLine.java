package ca.jrvs.apps.jdbc;

import java.math.BigDecimal;

public class OrderLine {
  private int quantity;
  private String productCode;
  private String productName;
  private String size;
  private String variety;
  private BigDecimal price;

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getVariety() {
    return variety;
  }

  public void setVariety(String variety) {
    this.variety = variety;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "OrderLine{" +
        "quantity=" + quantity +
        ", productCode='" + productCode + '\'' +
        ", productName='" + productName + '\'' +
        ", size='" + size + '\'' +
        ", variety='" + variety + '\'' +
        ", price=" + price +
        '}';
  }
}
