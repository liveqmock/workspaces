package com.iwgame.xnetty.centerMgr;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.iwgame.xnetty.model.ConncetModel;

public class ServerConnectedHander extends SimpleChannelUpstreamHandler {

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("有客户连接上来..." + e.getState());
		ctx.getChannel().write("success \r\n");
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.err.println("有客户失去连接...");

	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		
		CenterMgrServer.getChannelpool().put("agent1", ctx.getChannel());
		
		if (e.getMessage() instanceof String) {
			System.out.println("收到客户端的消息!\n" + e.getMessage() +" \r\n");
			
			
			
		} else if (e.getMessage() instanceof ConncetModel) {
			ConncetModel model = (ConncetModel) e.getMessage();
			System.out.println("收到客户端的消息!\n" + model.getUid());
			
//			System.out.println("收到oid:[" + model.getUid() + "]消息:");
//			System.out.println("hostname:" + model.getSystemInfo().getHostName());
//			System.out.println("IP:" + model.getSystemInfo().getIpAddress());
//			System.out.println("MAC:" + model.getSystemInfo().getMacAddress());
			
			ctx.getChannel().write("success \r\n");
		}

	}

}
