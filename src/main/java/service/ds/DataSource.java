package service.ds;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Roman on 15.05.2015.
 */
public class DataSource {
    final private static BasicDataSource dataSource;
    static {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("admin");
        ds.setPassword("Razwi=D");
        ds.setUrl("jdbc:mysql://myownradio.biz/myownradio");
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
