package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private final DataSource dataSource;

    public ProductDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Product get(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new GetStatementStrategy(id);
            preparedStatement = statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setTitle(resultSet.getString("title"));
                product.setPrice(resultSet.getInt("price"));
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return product;
    }


    public Long insert(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new InsertProductStatementStrategy(product);
            preparedStatement = statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            id = resultSet.getLong(1);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }

    public Product update(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new UpdateProductStatmentStrategy(product);
            preparedStatement = statementStrategy.makeStatement(connection);

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return product;
    }


    public void delete(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new DeleteProductStatementStratrgy(id);
            preparedStatement = statementStrategy.makeStatement(connection);

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
