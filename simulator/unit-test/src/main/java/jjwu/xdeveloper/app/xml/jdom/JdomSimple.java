/****************************************************************
 * 文件名 : JdomSimple.java
 * 日期 : 2013-5-23
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app.xml.jdom;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.junit.Test;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-5-23 下午4:18:40
 * @版本: v1.0.0
 */
public class JdomSimple {

	public Element getElementById(Element parentElement,String id){

		List<Element> elements = parentElement.getChildren();
		for (Element element : elements) {
			if(element.getAttributeValue("id").equalsIgnoreCase(id)){
				return element;
			}
		}
		return null;
	}

	@Test
	public void xmlTest() throws Throwable{
		final String xmlFilePath = "/Users/jjwu/Yunio/workspaces/simulator/unit-test/src/main/java/jjwu/xdeveloper/app/xml/sm_server.xml";
		final Document document = getDocument(xmlFilePath);
		final Element element = document.getRootElement();

		Element element2 = getElementById(element,"dx1");

		Element element3 = getElementById(element2.getChild("groups"),"game1");


		System.out.println(element3.getAttributeValue("name"));
		//		for (Element group : groups) {
		//			System.out.println(group.getParentElement().getAttributeValue("id"));
		//			System.out.println(group.getChild("group").getAttributeValue("id"));
		//			System.out.println(group.getChild("group").getChild("db").getChild("host").getValue());
		//			System.out.println(group.getChild("group").getChild("db").getChild("username").getValue());
		//			System.out.println(group.getChild("group").getChild("db").getChild("password").getValue());
		//		}

		//		final Element groups =
		//		groups.getChild("db").getChild("adapter").setText("MYSQL");
		//		writeXML(document, xmlFilePath);
	}

	public void writeXML(final Document document,final String xmlFilePath) throws Throwable{
		FileWriter fileWriter = null;
		final XMLOutputter outputter = new XMLOutputter();
		final Format format = Format.getCompactFormat();
		outputter.setFormat(format);
		fileWriter = new FileWriter(xmlFilePath);
		outputter.output(document, fileWriter);
		fileWriter.close();
	}


	public Document getDocument(final String xmlFilePath) throws Throwable{
		InputStreamReader reader = null;
		FileInputStream inputStream = null;
		Document document = null;
		try {
			final SAXBuilder builder = new SAXBuilder();
			inputStream = new FileInputStream(xmlFilePath);
			reader = new InputStreamReader(inputStream,"utf-8");
			document = builder.build(reader);
		} catch (final Exception e) {
			// TODO: handle exception
		}finally{
			if(reader != null){
				reader.close();
			}
			if(inputStream != null){
				inputStream.close();
			}
		}
		return document;
	}

}
