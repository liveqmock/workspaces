package jjwu.xdeveloper.java.mybatise;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * Hello world!
 * 
 */
public class App {

	public static void main(String[] args) throws IOException, Exception, InterruptedException {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 30000, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println("出发了事件");
			}
		});

		if(zk.exists("/root", false) == null){
			zk.create("/root", "mydata".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
		if(zk.exists("/root/childPath", false) == null){
			zk.create("/root/childPath", "mychilddata".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}

		System.out.println(new String(zk.getData("/root/mychilddata",false,null)));
	}
}
