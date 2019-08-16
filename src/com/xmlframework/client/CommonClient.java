package com.xmlframework.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.log.ILog;
import com.log.LogTypeEnum;
import com.log.LoggerFactory;
import com.xmlframework.annoscan.util.PropertiesUtil;
import com.xmlframework.handler.MessageHandler;
import com.xmlframework.handler.XmlHandler;
import com.xmlframework.server.CommonServer;

public class CommonClient {
	
	private static ILog logger = LoggerFactory.getLogger(CommonClient.class, LogTypeEnum.FILEWITHLOCK);
	
	/**
	 * 服务端地址
	 */
	private String addr;
	/**
	 * 服务端端口
	 */
	private int port;
	
	/**
	 * 信息初始化
	 */
	private void init() {
		this.addr = PropertiesUtil.getPropByName("socket.addr");
		this.port = Integer.valueOf(PropertiesUtil.getPropByName("socket.port"));
	}
	
	public void connectAndCommunicate() {
		this.init();
		
		// request
		String reqXml = "D:\\idea-workspace\\xml-framework\\src\\add_req.xml";
		// response
		String respXml = "D:\\idea-workspace\\xml-framework\\src\\add_resp.xml";
		
		try (SocketChannel sc = SocketChannel.open()) {
			sc.connect(new InetSocketAddress(this.addr, this.port));
			logger.info("与 {}:{} 进行通信", this.addr, this.port);
			
			ByteBuffer buffer = ByteBuffer.allocate(1024);

			// 根据文件内容发送报文
			logger.info("读取 {} 文件内容并发送给服务端", reqXml);
			String str = XmlHandler.xmlFileToString(reqXml);
			String sendMsg = MessageHandler.addLenPrefix(str);
			buffer.put(sendMsg.getBytes());
			buffer.flip();
			sc.write(buffer);

			// 将响应值写回文件
			logger.info("接收服务端返回数据，并写入文件 {}", respXml);
			String data = readData(sc);
			XmlHandler.xmlStringToFile(data, respXml);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	/**
	 * 从 xml string中读出 data
	 * 
	 * @param sc
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private static String readData(SocketChannel sc) throws NumberFormatException, IOException {
		int dataLen = Integer.valueOf(MessageHandler.readByLen(sc, CommonServer.PREFIX_LEN));

		String data = MessageHandler.readByLen(sc, dataLen);

		return data;
	}
	
	public static void main(String[] args) throws Exception {
		new CommonClient().connectAndCommunicate();
	}

}
