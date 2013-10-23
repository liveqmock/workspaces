package com.iwgame.xnetty.agentNode;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class AgentNodeHandler extends SimpleChannelUpstreamHandler {

	@Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception  {
//	    	System.out.println("---handleUpstream---");
	        if (e instanceof ChannelStateEvent) {
	        }
	        super.handleUpstream(ctx, e);
    }

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String msg = e.getMessage().toString();
		System.out.println("收到服务端发送的数据  ---> " + msg);
		if (msg.startsWith("success")) {
			System.out.println("服务端返回连接成功...");
			//定时发心跳到server端
		} else {
			try {
				ScriptConsole.exec(ctx.getChannel());
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
			System.out.println("其他消息....");
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelConnected");
		super.channelConnected(ctx, e);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelClosed");
		super.channelClosed(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		try {
			new Thread(new AgentReStart()).start();
			System.out.println("channelDisconnected");
			super.channelDisconnected(ctx, e);
		} catch (Throwable e2) {
			e2.printStackTrace();
		}
		
	}


}
