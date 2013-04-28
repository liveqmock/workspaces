package jjwu.xdeveloper.app.unit;

import java.util.ArrayList;
import java.util.List;

import jjwu.xdeveloper.app.model.Monitor;
import jjwu.xdeveloper.app.model.Mqqueue;
import jjwu.xdeveloper.app.model.Mqqueues;
import jjwu.xdeveloper.app.model.Mqtotal;
import jjwu.xdeveloper.app.model.Mqtotals;
import jjwu.xdeveloper.app.model.Queue;
import jjwu.xdeveloper.app.model.Queues;
import jjwu.xdeveloper.app.xml.unit.BeanXMLMapping;

public class AppTest {
	
	public static void main(String[] args) {
		Monitor monitor = new Monitor();
		monitor.setPassword("guest");
		monitor.setUsername("guest");
		
		Mqtotal mqtotal = new Mqtotal();
		mqtotal.setHost("127.0.0.1");
		mqtotal.setMaxwarn(100);
		mqtotal.setPhones("13776801367");
		
		Mqtotals mqtotals = new Mqtotals();
		List<Mqtotal> list = new ArrayList<Mqtotal>();
		list.add(mqtotal);
		mqtotals.setList(list);
		monitor.setMqtotals(mqtotals);
		
		
		
		List<Mqqueue> mqqueuelist = new ArrayList<Mqqueue>();
		List<Queue> queuelist = new ArrayList<Queue>();
		Queue queue = new Queue();
		queue.setName("queue.name.mail");
		queue.setMaxwarn(0);
		queuelist.add(queue);
		
		Queues queues = new Queues();
		queues.setList(queuelist);
		Mqqueue mqqueue = new Mqqueue();
		mqqueue.setHost("127.0.0.1");
		mqqueue.setVirtualhost("task-p1-game");
		mqqueue.setPhones("13776801367");
		mqqueue.setQueues(queues);
		
		mqqueuelist.add(mqqueue);
		
		Mqqueues mqqueues = new Mqqueues();
		mqqueues.setList(mqqueuelist);
		monitor.setMqqueues(mqqueues);
		
		
		System.out.println(BeanXMLMapping.toXML(monitor));
	}

}
