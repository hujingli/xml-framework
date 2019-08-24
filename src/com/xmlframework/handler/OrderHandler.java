package com.xmlframework.handler;

import com.jdbc.OrderDao;
import com.jdbc.StockDao;
import com.xmlframework.entity.Order;

import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
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
        Order order = queue.poll();

        try {
            int affectedCount = OrderDao.insert(order);

            if (affectedCount == 1) {
                StockDao.descStock(order.getItemCode());
                // log
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
