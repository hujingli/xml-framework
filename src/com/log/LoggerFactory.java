package com.log;

import java.io.IOException;

import com.log.lock.FileLockLogger;
import com.log.queue.FileQueueLogger;

/**
 * 日志工厂方法，所有需要日志记录的都使用此工厂获取
 * 
 * @author wanglw2
 *
 */
public class LoggerFactory {
	/**
	 * 获取日志记录的工厂方法
	 * 
	 * @param clazz
	 * @return
	 */
	public static ILog getLogger(Class<?> clazz, LogTypeEnum type) {
		ILog logger;
		switch (type) {
			case CONSOLE:
				logger = new SimpleLogger(clazz);
				break;
			case FILEWITHLOCK:
				logger = new FileLockLogger(clazz);
				break;
			case FILEWITHQUEUE:
				logger = new FileQueueLogger(clazz);
				break;
			default:
				logger = null;
		}
		return logger;
	}

	public static void main(String[] a) throws IOException {
		ILog clog = LoggerFactory.getLogger(LoggerFactory.class, LogTypeEnum.CONSOLE);
		ILog flog = LoggerFactory.getLogger(LoggerFactory.class, LogTypeEnum.FILEWITHLOCK);
		ILog qlog = LoggerFactory.getLogger(LoggerFactory.class,  LogTypeEnum.FILEWITHQUEUE);

		clog.debug("开始计算用户：[{}]的薪资", "tom");
		flog.debug("开始计算用户：[{}]的薪资", "tom");
		qlog.debug("开始计算用户：[{}]的薪资", "tom");

		try {
			throw new NullPointerException("null");
		} catch (Exception e) {
			clog.error("error", e);
			flog.error("error", e);
			qlog.error("error", e);
		}

	}
}
