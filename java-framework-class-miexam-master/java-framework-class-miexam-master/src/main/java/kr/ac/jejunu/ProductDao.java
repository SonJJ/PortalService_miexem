package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private final kr.ac.jejunu.jdbcContext jdbcContext = new jdbcContext();

    public ProductDao(DataSource dataSource){
        this.jdbcContext.dataSource = dataSource;
    }

    public Product get(Long id) throws SQLException {
            StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return jdbcContext.GetForProduct(statementStrategy);
    }
    public Long insert(Product product) throws SQLException {
        StatementStrategy statementStrategy = new InsertProductStatementStrategy(product);
        return jdbcContext.insertForProduct(statementStrategy);
    }
    public Product update(Product product) throws SQLException {
        StatementStrategy statementStrategy = new UpdateProductStatmentStrategy(product);
        jdbcContext.updateForProduct(statementStrategy);
        return product;
    }
    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteProductStatementStratrgy(id);
        jdbcContext.updateForProduct(statementStrategy);
    }
}
