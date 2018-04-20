package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private final kr.ac.jejunu.jdbcContext jdbcContext = new jdbcContext();

    public ProductDao(DataSource dataSource){
        this.jdbcContext.dataSource = dataSource;
    }

    public Product get(Long id) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
                preparedStatement.setLong(1, id);
                return preparedStatement;

        };
        return jdbcContext.GetForProduct(statementStrategy);
    }
    public Long insert(Product product) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product(title, price) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, product.getTitle());
                preparedStatement.setInt(2, product.getPrice());
                preparedStatement.executeUpdate();
                return preparedStatement;

        };
        return jdbcContext.insertForProduct(statementStrategy);
    }
    public Product update(Product product) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET title = ? , price = ? WHERE id = ?");
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
            return preparedStatement;

        };
        jdbcContext.updateForProduct(statementStrategy);
        return product;
    }
    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?" );
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                return preparedStatement;
        };
        jdbcContext.updateForProduct(statementStrategy);
    }
}
