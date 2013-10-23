	package com.iwgame.xnetty.centerMgr;

import static org.jboss.netty.channel.Channels.pipeline;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * 创建一个netty server端
 */
public class CenterMgrServer {
	
	private static final Hashtable<String,Channel> channelPool = new Hashtable<String, Channel>();

	public void run() {
		NioServerSocketChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.setFactory(factory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = pipeline();
//				pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
//				pipeline.addLast("encoder", new ObjectEncoder());
				
				pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()));
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("handler", new ServerConnectedHander());
				
//				pipeline.addLast("framer", new DelimiterBasedFrameDecoder(1,Delimiters.lineDelimiter()));
				return pipeline;
			}
		});
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		SocketAddress address = new InetSocketAddress(9090);
		bootstrap.bind(address);
		System.out.println("CenterMgrServer Starting Success ....");
	}
	
	

	public static Hashtable<String, Channel> getChannelpool() {
		return channelPool;
	}
	
	public static void writeChannel(String oid,String cmd){
		if(channelPool!=null && !channelPool.isEmpty()){
			channelPool.get(oid).write(cmd+" \r\n");
		}else{
			System.err.println("此通道不存在");
		}
	}



	public static void main(String[] args) {
		CenterMgrServer server = new CenterMgrServer();
		server.run();
		
		Scanner scanner = new Scanner(System.in);
		while(true){
			String cmd = scanner.nextLine();
			CenterMgrServer.writeChannel("agent1", cmd);
		}
		
	}
}
