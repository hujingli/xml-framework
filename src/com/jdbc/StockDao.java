package com.jdbc;

import com.xmlframework.entity.Stock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockDao {

    public static int descStock(String itemCode) throws SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DBUtil.getConnection();
            st = conn.createStatement();

            boolean updated = st.execute("update stock set qty = qty - 1 where item_code = '"+itemCode+"'");
            return updated ? 1 : 0;
        } finally {
            DBUtil.closeResource(conn, st, null);
        }
    }

    public static List<Stock> listStocks() throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        List<Stock> stocks = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("select item_code, qty from stock");

            while(rs.next()) {
                Stock stock = new Stock();
                stock.setItemCode(rs.getString("item_code"));
                stock.setQty(rs.getInt("qty"));

                stocks.add(stock);
            }

            return stocks;
        } finally {
            DBUtil.closeResource(conn, st, rs);
        }
    }

}
