package com.log;

import com.log.lock.FileLockLogger;
import com.log.queue.FileQueueLogger;

import java.io.IOException;

/**
 * ��־����������������Ҫ��־��¼�Ķ�ʹ�ô˹�����ȡ
 * 
 * @author wanglw2
 *
 */
public class LoggerFactory {
	/**
	 * ��ȡ��־��¼�Ĺ�������
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

		clog.debug("��ʼ�����û���[{}]��н��", "tom");
		flog.debug("��ʼ�����û���[{}]��н��", "tom");
		qlog.debug("��ʼ�����û���[{}]��н��", "tom");

		try {
			throw new NullPointerException("null");
		} catch (Exception e) {
			clog.error("error", e);
			flog.error("error", e);
			qlog.error("error", e);
		}

	}
}
