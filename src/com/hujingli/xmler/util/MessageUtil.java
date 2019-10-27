package com.hujingli.xmler.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MessageUtil {

    private static final int PREFIX_LEN = 6;

    public static String checkoutData(InputStream is) throws IOException {
        int dataLen = Integer.parseInt(readByLength(is, PREFIX_LEN));
        return readByLength(is, dataLen);
    }

    private static String readByLength(InputStream is, int len) throws IOException {
        byte[] readBytes = new byte[len];
        List<Byte> list = new ArrayList<>();

        int readLen;
        int totalLen = 0;
        while(totalLen < len && (readLen = is.read(readBytes)) > 0) {
            totalLen += readLen;

            for(int i = 0; i < readLen; i++) {
                list.add(readBytes[i]);
            }

            readBytes = new byte[1024];
        }

        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }

        return (new String(bytes)).trim();
    }

    public static String objectToXmlStringWithPrefix(Object obj) throws JAXBException {
        String xmlStr = objectToXmlString(obj);
        return addPrefix(xmlStr);
    }

    private static String addPrefix(String xmlStr) {
        int dataLen = xmlStr.getBytes().length;
        String prefix = String.format("%06d", dataLen);

        return prefix + xmlStr;
    }

    public static <T> Object xmlStringToObject(String xmlStr, Class<T> clz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader sr = new StringReader(xmlStr);
        return unmarshaller.unmarshal(sr);
    }

    private static String objectToXmlString(Object obj) throws JAXBException {
        StringWriter sw = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, sw);

        return sw.toString();
    }

}
