package com.jdbc;

import java.sql.*;

public class DBUtil {

    public static String url = "jdbc:mysql://localhost:3306/cyvan?serverTimezone=UTC";
    public static String user = "root";
    public static String passwd = "root";

    // TODO(cyvan):更换连接池
    public static Connection getConnection() throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(url, user, passwd);

        return conn;
    }

    public static void closeResource(Connection conn, Statement st, ResultSet rs) throws SQLException {
        if (rs != null) rs.close();
        if (st != null) st.close();
        if (conn != null) conn.close();
    }

}
