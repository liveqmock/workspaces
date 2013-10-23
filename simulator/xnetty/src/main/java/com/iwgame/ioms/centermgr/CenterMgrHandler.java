package com.iwgame.ioms.centermgr;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class CenterMgrHandler extends SimpleChannelUpstreamHandler {

	private final ThreadLocal<Boolean> COMMAND_FLAG = new ThreadLocal<Boolean>();

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		if (e instanceof ChannelStateEvent) {
			System.out.println("Channel state changed: " + e);
		}
		super.handleUpstream(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out.println("messageReceived!");
		String request = (String) e.getMessage();
		if (request.toLowerCase().equals("bye")) {
			ChannelFuture future = e.getChannel().write("bye\r\n");
			future.addListener(ChannelFutureListener.CLOSE);
		} else if (request.startsWith("I'm")) {
			String client = request.substring(4);
			ChannelPool.getInstance().add(client, ctx);
		} else if (request.startsWith("id")) {
			System.out.println("recevie msg ==" + request);
		} else {
			ctx.sendUpstream(e);
			System.out.println("send up==" + request);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		ChannelPool.getInstance().remove(ctx);
		e.getChannel().close();

	}

}
