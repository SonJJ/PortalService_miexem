package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private final kr.ac.jejunu.jdbcContext jdbcContext = new jdbcContext();

    public ProductDao(DataSource dataSource){
        this.jdbcContext.dataSource = dataSource;
    }

    public Product get(Long id) throws SQLException {
        String sql = "select * from product where id = ?";
        Object[] params = new Object[]{id};
        return jdbcContext.get(sql, params);
    }

    public Long insert(Product product) throws SQLException {
        Object[] params = new Object[]{product.getTitle(), product.getPrice()};
        String sql = "INSERT INTO product(title, price) VALUES (?,?)";
        return jdbcContext.insert(params, sql);
    }

    public Product update(Product product) throws SQLException {
        String sql = "UPDATE product SET title = ? , price = ? WHERE id = ?";
        Object[] params = new Object[]{product.getTitle(), product.getPrice(), product.getId()};
        jdbcContext.update(sql, params);
        return product;
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";
        Object[] params = new Object[]{id};
        jdbcContext.update(sql, params);
    }
}
