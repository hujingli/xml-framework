package com.log.queue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 队列日志打印管理器，使用 queue 接收日志信息并启用守护线程处理日志信息
 * @author cyvan
 *
 */
public class QueueManager extends Thread {

	/**
	 * 日志queue
	 */
	private final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
	
	/**
	 * 日志文件地址
	 */
	private String logPath;
	
	public QueueManager(String logPath) {
		this.logPath = logPath;
	}
	
	/**
	 * 生产消息
	 * @param logInfo
	 * @return
	 */
	public boolean produce(String logInfo) {
		return queue.add(logInfo);
	}
	
	/**
	 * 以守护线程的方式处理日志消息
	 */
	@Override
	public void run() {
		try(FileWriter fw = new FileWriter(new File(this.logPath), true)) {
			while(true) {
				if (!queue.isEmpty()) {
					String logInfo = queue.poll();
					StringBuffer sb = new StringBuffer();
					sb.append("[QUEUELOGGER]").append(logInfo).append(System.getProperty("line.separator"));
					sb.trimToSize();
					try {
						fw.write(sb.toString());
						fw.flush();
					} catch (IOException e) {
						this.queue.add(e.toString());
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
