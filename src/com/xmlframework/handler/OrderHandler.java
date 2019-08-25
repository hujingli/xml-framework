package com.xmlframework.handler;

import com.jdbc.DBUtil;
import com.xmlframework.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单handler，后台线程持续插入
 */
// TODO(cyvan): 修改为 ThreadPool
public class OrderHandler implements Runnable {

    private final AtomicInteger ai = new AtomicInteger(0);

    private Queue<Order> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(!queue.isEmpty()) {
            Order[] orders = queue.toArray(new Order[0]);
            System.out.println("write db total: " + orders.length);

//            try {
//                int[] results = OrderDao.batchInsertWithoutCloseConn(orders, conn);

//                Map<String, Integer> map = new HashMap<>();
//                for(int i = 0; i < results.length; i++) {
//                    if (results[i] == 0) {
//                        Order failed = orders[i];
//                        failed.setOrderStatus(3);
//                        this.produce(failed);
//                        if (map.containsKey(orders[i].getItemCode())) {
//                            map.put(orders[i].getItemCode(), map.get(orders[i].getItemCode()) + 1);
//                        } else {
//                            map.put(orders[i].getItemCode(), 1);
//                        }
//                    }
//                }

//                if (!map.isEmpty()) {
//                    for(Map.Entry<String, Integer> entry : map.entrySet()) {
//                        // 插入失败则返还数量
//                        StockHandler.incrStock(entry.getKey(), entry.getValue());
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生产 order
     * @param o order
     * @return true/false
     */
    public boolean produce(Order o) {
        return this.queue.add(o);
    }

    /**
     * 获取序列号
     * @return int
     */
    public int getSequence() {
        return ai.incrementAndGet();
    }

    /**
     * 检测序列号石佛铺在queue中
     * @return true/false
     */
    public boolean checkSequence(String sequence) {
        for(Order order : queue) {
            if (order.getOrderSequence().equals(sequence)) {
                return true;
            }
        }
        return false;
    }
}
