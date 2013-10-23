package com.iwgame.ioms.centermgr;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class CenterMgrServer {
	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(new CenterMgrServerPipelineFactory());

		// 因为我要用到长连接
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		SocketAddress address = new InetSocketAddress("127.0.0.1", 9090);
		bootstrap.bind(address);
		System.out.println("bind is ok");
	}
}
