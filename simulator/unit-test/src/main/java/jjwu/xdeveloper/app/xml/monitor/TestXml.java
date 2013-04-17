package jjwu.xdeveloper.app.xml.monitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;



import com.thoughtworks.xstream.XStream;

public class TestXml {

	
	public static void main(String[] args) throws Exception {
		XStream xstream = new XStream();
		xstream.alias("queue", Queue.class);
		xstream.alias("mqtotal", Mqtotal.class);
		xstream.alias("mqqueue", Mqqueue.class);
		String xml = readFile("/Users/jjwu/Yunio/workspace/simulator/unit-test/src/main/java/jjwu/xdeveloper/app/xml/example.xml");
		Monitor bean = (Monitor) xstream.fromXML(xml);
		System.out.println(bean.getMqqueues().get(0).getHost());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static String readFile(String fileName) throws Exception {
		String fileContent = "";
		File f = new File(fileName);
		FileReader fileReader = new FileReader(f);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = "";
		while ((line = reader.readLine()) != null) {
			fileContent = fileContent + line;
		}
		reader.close();
		return fileContent;
	}
}
