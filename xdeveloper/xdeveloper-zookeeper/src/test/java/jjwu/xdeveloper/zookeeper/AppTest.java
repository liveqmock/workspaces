package jjwu.xdeveloper.zookeeper;

import jjwu.xdeveloper.zookeeper.curator.ZkCacheClient;
import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    private final static String nodePath = "/xcloud-sms-providers/hq";
    private final static String nodeRoot = "/xcloud-sms-providers";
    
    @Test
    public void testApp1() throws Throwable {

        try {
            ZkCacheClient client = new ZkCacheClient("127.0.0.1:2181");
            client.start();
            client.watcherPath(nodeRoot);
            client.watherNode(nodePath);
            while(true){
                Thread.sleep(1000);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(true);
    }
    
    @Test
    public void testApp2() {

        try {
            ZkCacheClient client = new ZkCacheClient("127.0.0.1:2181");
            client.start();
            client.deleteData(nodePath);
            client.setData(nodePath, "love".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue(true);
    }
}
