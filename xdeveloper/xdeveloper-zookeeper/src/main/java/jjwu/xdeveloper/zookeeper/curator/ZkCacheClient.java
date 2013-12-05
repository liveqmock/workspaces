package jjwu.xdeveloper.zookeeper.curator;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.shared.SharedValue;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.google.common.io.Closeables;

public class ZkCacheClient {
    
    private static CuratorFramework                         client     = null;
    
    private final String                             zkhost;
    
    private final ConcurrentLinkedQueue<Closeable>   cacheList  = new ConcurrentLinkedQueue<Closeable>();
    
    private final ConcurrentMap<String, SharedValue> cacheValue = new ConcurrentHashMap<String, SharedValue>();
    
    public ZkCacheClient(String zkhost) {

        this.zkhost = zkhost;
    }
    
    public void start() {

        try {
            stop();
            client = CuratorFrameworkFactory.newClient(zkhost, new ExponentialBackoffRetry(1000, 5));
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void stop() throws Exception {

        try {
            for (Closeable closeable : cacheList) {
                Closeables.close(closeable, false);
            }
            cacheList.clear();
            
            for (SharedValue sharedValue : cacheValue.values()) {
                sharedValue.close();
            }
            cacheValue.clear();
            
            if (client != null) {
                Closeables.close(client, false);
                client = null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void setData(String nodePath, byte[] data) throws Exception {

        SharedValue sharedValue = cacheValue.get(nodePath);
        if (sharedValue == null) {
            sharedValue = new SharedValue(client, nodePath, new String("").getBytes());
            sharedValue.start();
            cacheValue.put(nodePath, sharedValue);
        }
        sharedValue.setValue(data);
    }
    
    public void deleteData(String nodePath) throws Exception {

        client.delete().forPath(nodePath);
    }
    
    public byte[] getData(String nodePath) throws Exception {

        SharedValue sharedValue = cacheValue.get(nodePath);
        if (sharedValue == null) {
            sharedValue = new SharedValue(client, nodePath, new String("").getBytes());
            sharedValue.start();
            cacheValue.put(nodePath, sharedValue);
        } else {
            return sharedValue.getValue();
        }
        return getData(nodePath);
    }
    
    public Properties getNodeProperties(String nodePath) {

        try {
            Properties props = new Properties();
            byte[] datas = getData(nodePath);
            if (null != datas) {
                props.load(new ByteArrayInputStream(datas));
            }
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void watcherPath(String nodePath) throws Throwable {

        final PathChildrenCache cache = new PathChildrenCache(client, nodePath, false);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {

                switch (event.getType()) {
                    case CHILD_UPDATED:
                        System.out.println("更新节点");
                        break;
                    case CHILD_REMOVED:
                        System.out.println("删除节点");
                        break;
                    case CHILD_ADDED:
                        System.out.println("添加节点");
                        break;
                    default:
                        System.out.println("defautl");
                        break;
                }
            }
        });
        cache.start(StartMode.BUILD_INITIAL_CACHE);
    }
    
    public void watherNode(String nodePath) throws Throwable {

        final NodeCache cache = new NodeCache(client, nodePath);
        cache.getListenable().addListener(new NodeCacheListener() {
            
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("修改属性值");
            }
        });
        cache.start(true);
    }
    
    public void createPath(String path) throws Exception{
        client.create().forPath(path);
    }
    
    public Stat existsPath(String path) throws Exception{
        return client.checkExists().forPath(path);
    }
    public void deletePath(String path) throws Exception{
         client.delete().forPath(path);
    }
    
}
