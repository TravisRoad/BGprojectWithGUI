package util;

import org.dom4j.*;

import javax.xml.parsers.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;


public class XMLparser {
    private Document document;
    private String fileName;

    public void init() {
    }

    public void parserXml(String strXML) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(strXML);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();// 指向根节点

        Iterator it = root.elementIterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();// 一个Item节点
            Attribute attr = element.attribute("objectid");
            String str = attr.getValue();
            int id = 0;
            if (attr != null) {
                id = Integer.parseInt(attr.getValue());
            }
            System.out.println(element.getName() + " : " + str);
        }
    }

    public static void main(String[] args) {
        XMLparser xmLparser = new XMLparser();
        xmLparser.parserXml("file:src/resource/a.xml");
    }
}
