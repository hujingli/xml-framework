package com.jdbc;

import com.xmlframework.entity.Order;

import java.sql.*;

public class OrderDao {


    public static int insert(Order order) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        int affected = 0;
        try {
            conn = DBUtil.getConnection();
            pst = conn.prepareStatement("insert into `order`(order_id, item_code, order_qty, order_sequence, order_status, order_date, order_time, order_user) values(?,?,?,?,?,?,?,?)");

            pst.setString(1, order.getOrderId());
            pst.setString(2, order.getItemCode());
            pst.setInt(3, order.getOrderQty());
            pst.setString(4, order.getOrderSequence());
            pst.setInt(5, order.getOrderStatus());
            pst.setString(6, order.getOrderDate());
            pst.setString(7, order.getOrderTime());
            pst.setString(8, order.getOrderUser());

            boolean inserted = pst.execute();
            affected = inserted ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(conn, pst, null);
        }

        return affected;
    }

    public static Order getBySequence(String sequence) throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        Order order = null;
        try {
            conn = DBUtil.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery("select order_id, item_code, oreder_qty, order_sequence, order_status, order_date, order_time, order_user from `order` where sequence = '"+sequence+"'");

            while(rs.next()) {
                order = new Order();
                order.setOrderId(rs.getString("order_id"));
                order.setItemCode(rs.getString("item_code"));
                order.setOrderQty(rs.getInt("order_qty"));
                order.setOrderSequence(rs.getString("order_sequence"));
                order.setOrderStatus(rs.getInt("order_status"));
                order.setOrderDate(rs.getString("order_date"));
                order.setOrderTime(rs.getString("order_time"));
                order.setOrderUser(rs.getString("order_user"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(conn, st, rs);
        }

        return order;
    }

}
