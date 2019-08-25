package com.xmlframework.handler;

import com.jdbc.StockDao;
import com.xmlframework.entity.Stock;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品 handler，维护内存中的商品数量
 */
public class StockHandler {

    private static final int STOCK_TYPE_NUMBER = 10;

    private static Map<String, Integer> stock = new HashMap<>(STOCK_TYPE_NUMBER);

    public static void init() {
        // get from db
        List<Stock> stocks = null;
        try {
            stocks = StockDao.listStocks();

            // insert
            for (Stock s : stocks) {
                stock.put(s.getItemCode(), s.getQty());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 减少库存
     * @param id 商品 id
     * @return true / false
     */
    public static boolean descStock(String id) {
        if (stock.get(id) > 0) {
            synchronized (id.intern()) {
                if (stock.get(id) > 0) {
                    stock.put(id, stock.get(id) - 1);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 增加库存
     * @param id 商品 id
     */
    public static void incrStock(String id, int count) {
        synchronized (id.intern()) {
            stock.put(id, stock.get(id) + count);
        }
    }

    /**
     * 获取库存
     * @return map
     */
    public static Map<String, Integer> getStock() {
        // TODO(cyvan): clone
        return stock;
    }

}
