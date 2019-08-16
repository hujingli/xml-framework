package com.xmlframework.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * socket message 解析工具类
 * 
 * @author cyvan
 *
 */
public class MessageHandler {

	/**
	 * 从SocketChannel中读取指定长度的字节信息
	 * 
	 * @param sc
	 * @param len
	 * @return
	 * @throws IOException
	 */
	public static String readByLen(SocketChannel sc, int len) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(len);
		List<Byte> list = new ArrayList<>();

		int readLen = 0;
		int totalLen = 0;
		// 未读完指定长度值，需循环继续读取
		while (totalLen < len && (readLen = sc.read(buffer)) > 0) {
			totalLen += readLen;
			buffer.flip();

			// 将byte放入list最后构造成string返回
			while (buffer.hasRemaining()) {
				list.add(buffer.get());
			}
		}

		byte[] bytes = new byte[list.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = list.get(i);
		}
		String string = (new String(bytes)).trim();
		return string;
	}

	/**
	 * 在msg加上6位的msg长度，不足6位用0填充
	 * 
	 * @param msg
	 * @return
	 */
	public static String addLenPrefix(String msg) {
		int dataLen = msg.getBytes().length;
		String prefix = String.format("%06d", dataLen);

		return new StringBuilder().append(prefix).append(msg).toString();
	}

}
