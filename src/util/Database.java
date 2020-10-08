package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bgprojectwithgui";
    private static final String userName = "root";
    private static final String passwd = "123456";
    private static Connection conn = null;

    static{//静态代码块
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, userName, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * connect
     * @deprecated
     */
    public static void connect() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, userName, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws SQLException {
        return conn;
    }

    /**
     * 用于关闭connection
     * @return 是否成功关闭
     */
    public static boolean close(){
        boolean flag = false;
        try {
            conn.close();
            flag = true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            return flag;
        }
    }
}
