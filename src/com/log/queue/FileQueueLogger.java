package com.log.queue;

import com.log.ILog;
import com.log.LogEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * 文件日志，将日志写入文件并在控制台打印
 * 使用 queue 代替 synchronized 减少文件句柄操作，提升性能
 * @author cyvan
 *
 */
public class FileQueueLogger implements ILog {
	
	static {
		String logL = System.getProperty("LOGLEVEL", "DEBUG");
		logLevel = LogEnum.getLevelByName(logL);
	}
	
	/**
	 * 日志级别
	 */
	private static int logLevel = 0;
	
	/**
	 * 日志存放文件地址
	 */
	private String logFilePath = "D:\\demo.log";
	
	/**
	 * 日志队列管理器
	 */
	private QueueManager queueManager = null;
	
	public FileQueueLogger(Class<?> clazz) {
		this.clazz = clazz;
		
		this.initLogger();
		
		this.initQueueManager();
	}
	
	/**
	 * 根据 logger.properties 初始化logger
	 */
	private void initLogger() {
		ResourceBundle resource = ResourceBundle.getBundle("logger");

		String path = resource.getString("log.filePath");

		if (path != null && !path.isEmpty()) {
			this.logFilePath = path;
		}

	}
	
	/**
	 * 根据 logFilePath 初始化 queueManager 并启动
	 */
	private void initQueueManager() {
		this.queueManager = new QueueManager(logFilePath);

		this.queueManager.start();
	}

	/**
	 * 使用日志记录的class
	 */
	private Class<?> clazz;

	/**
	 * 日志文本
	 * 
	 * @param logType
	 * @param msg
	 * @return
	 */
	private String getLogText(LogEnum logType, String msg) {
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS ");
		StringBuffer logSB = new StringBuffer();
		logSB.append(dFormat.format(new Date()));
		logSB.append((clazz == null ? "" : clazz.getName() + ":"));
		logSB.append(logType.getPreLogText()).append("-").append(msg);
		return logSB.toString();
	}

	/**
	 * 根据格式消息获取完整消息内容 例如：getMsg("证件号：{} 不存在 ","001002") -> "证件号：001002 不存在"
	 * 
	 * @param msg
	 * @param args
	 * @return
	 */
	private String getMsg(String msg, Object... args) {
		for (Object object : args) {
			msg = msg.replaceFirst("\\{\\}", object == null ? "null" : object.toString());
		}
		return msg;
	}
	
	/**
	 * send logInfo to logger queue
	 * @param logInfo
	 * @return
	 */
	private boolean sendToQueue(String logInfo) {
		return this.queueManager.produce(logInfo);
	}

	@Override
	public void info(String msg) {
		if (!showLog(LogEnum.INFO))
			return;
		String logT = getLogText(LogEnum.INFO, msg);
		System.out.println(logT);
		
		sendToQueue(logT);
	}

	@Override
	public void info(String msg, Throwable t) {
		if (!showLog(LogEnum.INFO))
			return;
		String logT = getLogText(LogEnum.INFO, msg);
		System.out.println(logT);
		t.printStackTrace(System.err);
		
		sendToQueue(logT);
	}

	@Override
	public void info(String msg, Object... args) {
		if (!showLog(LogEnum.INFO))
			return;
		String logT = getLogText(LogEnum.INFO, getMsg(msg, args));
		System.out.println(logT);
		
		sendToQueue(logT);
	}

	@Override
	public void warn(String msg) {
		if (!showLog(LogEnum.WARN))
			return;
		String logT = getLogText(LogEnum.WARN, msg);
		System.out.println(logT);
		
		sendToQueue(logT);
	}

	@Override
	public void warn(String msg, Throwable t) {
		if (!showLog(LogEnum.WARN))
			return;
		String logT = getLogText(LogEnum.WARN, msg);
		System.out.println(logT);
		t.printStackTrace(System.err);
		
		sendToQueue(logT);
	}

	@Override
	public void warn(String msg, Object... args) {
		if (!showLog(LogEnum.WARN))
			return;
		String logT = getLogText(LogEnum.WARN, getMsg(msg, args));
		System.out.println(logT);
		
		sendToQueue(logT);
	}

	@Override
	public void error(String msg) {
		if (!showLog(LogEnum.ERROR))
			return;
		String logT = getLogText(LogEnum.ERROR, msg);
		System.err.println(logT);
		
		sendToQueue(logT);
	}

	@Override
	public void error(String msg, Throwable t) {
		if (!showLog(LogEnum.ERROR))
			return;
		String logT = getLogText(LogEnum.ERROR, msg);
		System.err.println(logT);
		t.printStackTrace(System.err);
		
		sendToQueue(logT);
	}

	@Override
	public void error(String msg, Object... args) {
		if (!showLog(LogEnum.ERROR))
			return;
		String logT = getLogText(LogEnum.ERROR, getMsg(msg, args));
		System.err.println(logT);
		
		sendToQueue(logT);
	}

	@Override
	public void debug(String msg) {
		if (!showLog(LogEnum.DEBUG))
			return;
		String logT = getLogText(LogEnum.DEBUG, msg);
		System.out.println(logT);
		
		sendToQueue(logT);
	}

	@Override
	public void debug(String msg, Throwable t) {
		if (!showLog(LogEnum.DEBUG))
			return;
		String logT = getLogText(LogEnum.DEBUG, msg);
		System.out.println(logT);
		t.printStackTrace(System.out);
		
		sendToQueue(logT);
	}

	@Override
	public void debug(String msg, Object... args) {
		if (!showLog(LogEnum.DEBUG))
			return;
		String logT = getLogText(LogEnum.DEBUG, getMsg(msg, args));
		System.out.println(logT);
		
		sendToQueue(logT);
	}

	private boolean showLog(LogEnum logEnum) {
		return logEnum.getLogLevel() >= logLevel;
	}
	
}
