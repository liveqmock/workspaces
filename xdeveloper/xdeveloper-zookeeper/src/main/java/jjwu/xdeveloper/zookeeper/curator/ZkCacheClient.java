package jjwu.xdeveloper.zookeeper.curator;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedValue;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.google.common.io.Closeables;

public class ZkCacheClient {

	private CuratorFramework client = null;

	private final String zkhost;

	private final static String nodePath = "/xcloud-sms-providers";

	private final ConcurrentLinkedQueue<Closeable> cacheList = new ConcurrentLinkedQueue<Closeable>();

	private final ConcurrentMap<String, SharedValue> cacheValue = new ConcurrentHashMap<String, SharedValue>();

	public ZkCacheClient(String zkhost){
		this.zkhost = zkhost;
	}

	public void start(){
		try {
			stop();
			client = CuratorFrameworkFactory.newClient(zkhost, new ExponentialBackoffRetry(1000, 5));
			client.start();
			getNodeProperties(ZkCacheClient.nodePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stop() throws Exception{
		try {
			for (Closeable closeable : cacheList) {
				Closeables.close(closeable, false);
			}
			cacheList.clear();

			for (SharedValue sharedValue : cacheValue.values()) {
				sharedValue.close();
			}
			cacheValue.clear();

			if(client != null){
				Closeables.close(client, false);
				client = null;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void setData(String nodePath,byte[] data) throws Exception{
		SharedValue sharedValue = cacheValue.get(nodePath);
		if(sharedValue == null){
			sharedValue = new SharedValue(client, nodePath, new String("").getBytes());
			sharedValue.start();
			cacheValue.put(nodePath, sharedValue);
		}
		sharedValue.setValue(data);
	}

	public byte[] getData(String nodePath) throws Exception{
		SharedValue sharedValue = cacheValue.get(nodePath);
		if(sharedValue == null){
			sharedValue = new SharedValue(client, nodePath, new String("").getBytes());
			sharedValue.start();
			cacheValue.put(nodePath, sharedValue);
		}else{
			return sharedValue.getValue();
		}
		return getData(nodePath);
	}

	public Properties getNodeProperties(String nodePath){

		try {
			Properties props = new Properties();
			byte[] datas = getData(nodePath);
			if(null != datas){
				props.load(new ByteArrayInputStream(datas));
			}
			return props;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
