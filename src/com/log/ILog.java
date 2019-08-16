package com.log;

/**
 * ��־�ӿ�
 * 
 * @author wanglw2
 *
 */
public interface ILog {
	/**
	 * ��־��ʾ��Ϣ
	 * 
	 * @param msg
	 */
	public void debug(String msg);

	/**
	 * ��־��ʾ
	 * 
	 * @param msg
	 * @param t
	 */
	public void debug(String msg, Throwable t);

	/**
	 * ��־��ʾ
	 * 
	 * @param msg  ��ʾ��Ϣ
	 * @param args ��ʾ��Ϣ����
	 */
	public void debug(String msg, Object... args);

	/**
	 * ��־��ʾ��Ϣ
	 * 
	 * @param msg
	 */
	public void info(String msg);

	/**
	 * ��־��ʾ
	 * 
	 * @param msg
	 * @param t
	 */
	public void info(String msg, Throwable t);

	/**
	 * ��־��ʾ
	 * 
	 * @param msg  ��ʾ��Ϣ
	 * @param args ��ʾ��Ϣ����
	 */
	public void info(String msg, Object... args);

	/**
	 * ��־������Ϣ
	 * 
	 * @param msg
	 */
	public void warn(String msg);

	/**
	 * ��־����
	 * 
	 * @param msg
	 * @param t
	 */
	public void warn(String msg, Throwable t);

	/**
	 * ��־����
	 * 
	 * @param msg  ��ʾ��Ϣ
	 * @param args ��ʾ��Ϣ����
	 */
	public void warn(String msg, Object... args);

	/**
	 * ��־������Ϣ
	 * 
	 * @param msg
	 */
	public void error(String msg);

	/**
	 * ��־����
	 * 
	 * @param msg
	 * @param t
	 */
	public void error(String msg, Throwable t);

	/**
	 * ��־����
	 * 
	 * @param msg  ��ʾ��Ϣ
	 * @param args ��ʾ��Ϣ����
	 */
	public void error(String msg, Object... args);

}
