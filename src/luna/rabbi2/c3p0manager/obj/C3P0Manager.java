package luna.rabbi2.c3p0manager.obj;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * Created by Luna-C18 on 2017/7/11.
 */
public class C3P0Manager {
    // 数据源
    private static ComboPooledDataSource cpds = null;
    private C3P0Manager() {
        cpds = new ComboPooledDataSource();
    }

    public static C3P0Manager getC3P0Util(String address, String port, String database, String user, String pword, String ssl, int minAmount, int addAmount, int maxAmount) {
        C3P0Manager c3P0Util = new C3P0Manager();
        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl("jdbc:mysql://"+address+":"+port+"/"+database+"?useUnicode=true&characterEncoding=utf-8&useSSL="+ssl);
        cpds.setUser(user);
        cpds.setPassword(pword);
        cpds.setMinPoolSize(minAmount);
        cpds.setAcquireIncrement(addAmount);
        cpds.setMaxPoolSize(maxAmount);

        return c3P0Util;
    }

    public Connection getConnection() {
        try {
            return cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close(Connection connection) {
        if(connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void close(Statement statement) {
        if(statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void close(ResultSet resultSet) {
        if(resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void close() {
        cpds.close();
    }
}