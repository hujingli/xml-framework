package com.xmlframework.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import com.log.ILog;
import com.log.LogTypeEnum;
import com.log.LoggerFactory;
import com.xmlframework.annoscan.entity.ReqHandler;
import com.xmlframework.annoscan.handler.ClassLoadHandler;
import com.xmlframework.annoscan.handler.ControllerHandler;
import com.xmlframework.annoscan.util.PropertiesUtil;
import com.xmlframework.handler.MessageHandler;
import com.xmlframework.util.ReflectUtil;

/**
 * nio的socket server
 * 
 * @author cyvan
 *
 */
public class CommonServer {

	private static ILog logger = LoggerFactory.getLogger(CommonServer.class, LogTypeEnum.FILEWITHLOCK);

	/**
	 * 通信内容prefix长度
	 */
	public static final int PREFIX_LEN = 6;

	private int port;

	/**
	 * 完成“框架”启动服务
	 */
	private void init() {
		ClassLoadHandler.init();

		this.loadConfig();
	}

	/**
	 * 读取配置文件
	 */
	private void loadConfig() {
		this.port = Integer.parseInt(PropertiesUtil.getPropByName("socket.port"));
	}

	/**
	 * 开启服务，仅处理一种业务逻辑
	 */
	private void listenAndServe() {

		this.init();

		try (ServerSocketChannel ssChanel = ServerSocketChannel.open()) {
			// 绑定端口
			logger.info("服务端运行在 {} 端口，服务已启动", this.port);
			ssChanel.socket().bind(new InetSocketAddress(this.port));

			// 非阻塞
			ssChanel.configureBlocking(false);

			// 开启selector，绑定channel至selector并设置on_accept
			Selector selector = Selector.open();
			ssChanel.register(selector, SelectionKey.OP_ACCEPT);

			// process处理后的retMsg，用于isWritable时写回client
			Map<Object, String> retMsgMap = new Hashtable<>();
			while (true) {
				// select keys
				int n = selector.select();
				if (n == 0) { // 无就绪channel
					continue;
				}

				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();

					// 注意sc.register
					try {
						if (key.isAcceptable()) {
							SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
							sc.configureBlocking(false);
							sc.register(key.selector(), SelectionKey.OP_READ); // 设置read事件
						}

						if (key.isReadable()) {
							logger.info("感知到 read 事件，进行数据读取");
							SocketChannel sc = (SocketChannel) key.channel();
							String data = readFromSocket(sc);
							SelectionKey keyToWrite = sc.register(key.selector(), SelectionKey.OP_WRITE); // 设置write事件

							// 处理xml报文
							String retMsg = autoProcess(data);
							
							// 暂存信息
							retMsgMap.put(keyToWrite,retMsg);
							
						}

						if (key.isWritable()) {
							logger.info("感知到 write 事件，进行数据写回");
							try (SocketChannel sc = (SocketChannel) key.channel()) { 
								// 取出isReadable时存储的retMsg
								String retMsg = retMsgMap.get(key);
								
								// 删除暂存数据
								retMsgMap.remove(key);
								
								writeToSocket(sc, retMsg);
							}
						}
					} catch (Exception e) {
						logger.error(e.toString());
					}

					// remove key
					it.remove();
				}
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	/**
	 * 根据autoscan，半自动调用方法
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private String autoProcess(String data) throws Exception {
		// decode 出 reqCode
		// 根据 regex 取出<reqCode></reqCode>之间的具体业务代码，再从从Handler中获取所要执行方法的 Class 和 Method
		String code = extractCode(data);
		if ("".equals(code)) {
			// TODO(cyvan): 封装返回error header的方法
			return "";
		}

		logger.info("进行业务逻辑处理，业务代码为 {}", code);
		// invoke方法获得返回值
		ReqHandler handler = ControllerHandler.getHandler(code);

		logger.info("调用 {} 类的 {} 方法处理 {}", handler.getControllerClass(), handler.getActionMethod(), data);
		Object obj = ReflectUtil.invokeMethod(handler.getControllerClass(), handler.getActionMethod(), data);

		return (String) obj;
	}

	/**
	 * 从xml string中获取reqCode
	 * 
	 * @param data
	 * @return
	 */
	private String extractCode(String data) {
		String code = "";

		String pattern = "(<reqCode>)(.*?)(</reqCode>)";

		Pattern p = Pattern.compile(pattern);

		Matcher m = p.matcher(data);

		if (m.find()) {
			code = m.group(2);
		}

		return code;
	}

	/**
	 * 读取xml data
	 * 
	 * @param sc
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	private String readFromSocket(SocketChannel sc) throws IOException, JAXBException {
		int dataLen = Integer.parseInt(MessageHandler.readByLen(sc, PREFIX_LEN));
		return MessageHandler.readByLen(sc, dataLen);
	}

	/**
	 * 向client写回数据
	 * 
	 * @param sc
	 * @param msg
	 * @throws IOException
	 */
	private void writeToSocket(SocketChannel sc, String msg) throws IOException {
		// 加上data length 的 prefix
		String retMsg = MessageHandler.addLenPrefix(msg);

		ByteBuffer buffer = ByteBuffer.allocate(retMsg.getBytes().length);
		buffer.put(retMsg.getBytes(StandardCharsets.UTF_8));

		buffer.flip();
		while (buffer.hasRemaining()) {
			sc.write(buffer);
		}
	}

	public static void main(String[] args) {
		new CommonServer().listenAndServe();
	}

}
