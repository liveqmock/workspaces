package jjwu.xdeveloper.zookeeper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jjwu.xdeveloper.zookeeper.curator.ZkCacheClient;
import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {


	@Test
	public void testApp() {

		try {
			ZooKeeperOperator zkoperator = new ZooKeeperOperator();
			zkoperator.connect("127.0.0.1");
			Map<String,String> data = new HashMap<String, String>();
			data.put("username", "JJwu");
			data.put("password", "wujunjie");

			zkoperator.create("/root", null);

			System.out.println(Arrays.toString(zkoperator.getData("/root")));

			zkoperator.create("/root/child1", data.toString().getBytes());
			System.out.println(Arrays.toString(zkoperator.getData("/root/child1")));

			zkoperator.create("/root/child2", data.toString().getBytes());
			System.out.println(Arrays.toString(zkoperator.getData("/root/child2")));

			System.out.println("节点孩子信息:");
			zkoperator.getChild("/root");

			zkoperator.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertTrue(true);
	}

	@Test
	public void testApp2() {

		try {
			ZkCacheClient client = new ZkCacheClient("/xcloud-sms");
			client.start();

			client.setData("queue/", "3733".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertTrue(true);
	}
}
