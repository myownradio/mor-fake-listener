package service.ds;

import org.apache.commons.dbcp.BasicDataSource;
import tools.Props;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Roman on 15.05.2015.
 */
public class DataSource {
    final private static BasicDataSource dataSource;
    static {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(Props.getPropertyOrFail("database.driverClass"));
        ds.setUsername(Props.getPropertyOrFail("database.user"));
        ds.setPassword(Props.getPropertyOrFail("database.pass"));
        ds.setUrl(Props.getPropertyOrFail("database.url"));
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);
        ds.setDefaultAutoCommit(false);
        dataSource = ds;
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
