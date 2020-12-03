package util;

import java.sql.*;

/**
 * 用于连接数据库和数据库初始化
 */
public class Database {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://123.57.131.204:3306/boardgame" +
            "?useUnicode=true&autoReconnect=true&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8 ";
    private static final String userName = "root";
    private static final String passwd = "123456";
    private static Connection conn;


    public Database() {
        connect();
    }

    /**
     * connect to database
     *
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

    public Connection getConn() {
        return conn;
    }

    /**
     * 用于关闭connection
     *
     * @return 是否成功关闭
     */
    public boolean close() {
        boolean flag = false;
        try {
            conn.close();
            flag = true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            return flag;
        }
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) {
        Database d = new Database();
        Connection conn = d.getConn();
        String sql = "select * from user where username = ?";
        boolean ret = false;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, "LXY");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("username") + rs.getString("passwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
