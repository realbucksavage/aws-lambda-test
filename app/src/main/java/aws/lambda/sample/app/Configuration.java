package aws.lambda.sample.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Configuration {

    private static final DataSource dataSource;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        final var hostname = ensureEnvValue("DB_HOST");
        final var port = ensureEnvValue("DB_PORT");
        final var name = ensureEnvValue("DB_NAME");
        final var username = ensureEnvValue("DB_USER");
        final var password = ensureEnvValue("DB_PASS");

        final var cfg = new HikariConfig();
        cfg.setJdbcUrl("jdbc:postgresql://" + hostname + ":" + port + "/" + name);
        cfg.setUsername(username);
        cfg.setPassword(password);
        cfg.addDataSourceProperty("ssl", "false");

        dataSource = new HikariDataSource(cfg);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static String ensureEnvValue(String name) {
        final var value = System.getenv(name);
        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Environment variable " + name + " is required");
        }

        return value;
    }
}
