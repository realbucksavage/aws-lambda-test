package aws.lambda.sample.app.services.impl;

import aws.lambda.sample.app.Configuration;
import aws.lambda.sample.app.Todo;
import aws.lambda.sample.app.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.UUID;

public class DefaultTodoService implements TodoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTodoService.class);

    @Override
    public Todo addTodo(String todo) {

        final var sql = """
                INSERT INTO "todos" (id, task) VALUES (?, ?);
                """;

        final var newID = UUID.randomUUID().toString();
        try (final var conn = Configuration.getConnection(); final var statement = conn.prepareStatement(sql)) {
            statement.setString(1, newID);
            statement.setString(2, todo);

            final var insertedRows = statement.executeUpdate();

            LOGGER.debug("inserted {} rows", insertedRows);
        } catch (SQLException ex) {
            LOGGER.error("cannot open database connection", ex);
            throw new RuntimeException("connection failed");
        }

        return new Todo(newID, todo);
    }
}
