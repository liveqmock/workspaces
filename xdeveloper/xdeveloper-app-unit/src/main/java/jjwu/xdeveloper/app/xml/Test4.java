package jjwu.xdeveloper.app.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class Test4
{
	/**
	 * java object to xml
	 */
	
	private static XmlBean xmlBean;
	public static void main(String[] args) throws Exception
	{
		//instantiate the XStream class
		XStream xstream = new XStream();
		xstream.alias("step", Step.class);
		xstream.alias("action", Action.class);
		xstream.alias("flow", Flow.class);
		
		String xml =  readFile("/Users/jjwu/Yunio/workspace/xdeveloper/xdeveloper-app-unit/src/main/java/test.xml");
		System.out.println(xml);
		XmlBean bean = (XmlBean)xstream.fromXML(xml);
		System.out.println(bean.getFlow().getName());
		
		
	}
	/**
	 * ��ȡ�ļ�����
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	 public static String readFile(String fileName) throws Exception {
	    String fileContent = "";
		File f = new File(fileName);
		FileReader fileReader = new FileReader(f);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = "";
		while ((line = reader.readLine()) != null)
		{
			 fileContent = fileContent + line;
		}
		reader.close();
		return fileContent;
	}
}
