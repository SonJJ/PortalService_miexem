package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteProductStatementStratrgy implements StatementStrategy {
    private Long id;
    public DeleteProductStatementStratrgy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?" );
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        return preparedStatement;
    }
}
