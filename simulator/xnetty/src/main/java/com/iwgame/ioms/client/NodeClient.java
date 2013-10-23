package com.iwgame.ioms.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.iwgame.xnetty.model.ConncetModel;

public class NodeClient {
	public static boolean flag=true;
	public static boolean status=false;
	public static ClientBootstrap boostrap=new ClientBootstrap();
	public static Channel channel ;
	public static NodeClient client=new NodeClient();
	public NodeClient(){}
	public static NodeClient getInstance() {
	      return client;
	  }


	public static void keepLive() {

		try {
			if (status) {
				ConncetModel model = new ConncetModel();
				model.setUid("client1");
				channel.write(model);
			}
		} catch (Exception e) {
			status = false;
			System.out.println("channel Exception ");
		} finally {

		}


	}

	public static synchronized boolean init(int from) {
		System.out.println("init is run "+from);
		boostrap = new ClientBootstrap(
	                new NioClientSocketChannelFactory(
	                        Executors.newCachedThreadPool(),
	                        Executors.newCachedThreadPool()));

	        // Configure the pipeline factory.
	        boostrap.setPipelineFactory(new NodeClientPipelineFactory());
	        boostrap.setOption("child.tcpNoDelay", true);
	        boostrap.setOption("child.keepAlive", true);
	        // Start the connection attempt.
	        ChannelFuture future = boostrap.connect(new InetSocketAddress("127.0.0.1", 9090));
	        System.out.println("connect begin");
	        System.out.println("future . isSuccess is "+future.isSuccess());
	        channel= future.awaitUninterruptibly().getChannel();
	        if (!future.isSuccess()) {
	        	System.out.println("connect faile");
	        // Wait until the connection attempt succeeds or fails.
//	            future.getCause().printStackTrace();
	            boostrap.releaseExternalResources();
	            status=false;
	            System.out.println("connect failed;please restart app or notice admin");
	        }else{
	        	System.out.println("connect success");

	        	status=true;
	        	channel.write("I'm client1 \r\n");
	        	return true;
	        }
	        System.out.println(" init is end "+from);
	        return false;
	}
}
