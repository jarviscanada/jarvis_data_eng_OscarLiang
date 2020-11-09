package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCExecutor {

  final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

  public static void main(String[] args) {
    JDBCExecutor executor = new JDBCExecutor();
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
        "hplussport", "oscar", "password");
    try {
      Connection connection = dcm.getConnection();
      OrderDAO orderDAO = new OrderDAO(connection);
      Order order = orderDAO.findById(1000);
      System.out.println(order);
    } catch (SQLException e) {
      executor.logger.error(e.getMessage(), e);
    }
  }

}
