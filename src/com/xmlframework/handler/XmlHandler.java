package com.xmlframework.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * xml 数据解析工具类
 * 
 * @author cyvan
 *
 */
public class XmlHandler {

	/**
	 * 将xml的string转换成具体的pojo
	 * 
	 * @param <T>
	 * @param xmlStr
	 * @param clz
	 * @return
	 * @throws JAXBException
	 */
	public static <T> Object xmlStringToObject(String xmlStr, Class<T> clz) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		StringReader sr = new StringReader(xmlStr);
		return unmarshaller.unmarshal(sr);
	}

	/**
	 * 将object转换成xml的string
	 * 
	 * @param obj
	 * @return
	 * @throws JAXBException
	 */
	public static String objectToXmlString(Object obj) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(obj, sw);

		return sw.toString();
	}

	/**
	 * 获取xml文件内容
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String xmlFileToString(String path) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
			StringBuilder sb = new StringBuilder();
			Iterator<String> it = br.lines().iterator();
			while (it.hasNext()) {
				sb.append(it.next());
			}
			return sb.toString();
		}
	}

	/**
	 * 将xml string写入xml文件
	 * 
	 * @param str
	 * @param path
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static void xmlStringToFile(String str, String path) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(str)));

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	}
}
