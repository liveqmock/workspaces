package com.iwgame.ioms.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class NodeClientHandler extends SimpleChannelUpstreamHandler {

	private static final Logger logger = Logger.getLogger(NodeClientHandler.class.getName());

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		System.out.println("handleUpstream---");
		if (e instanceof ChannelStateEvent) {
			logger.info("handler==" + e.toString());
		}
		super.handleUpstream(ctx, e);

	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		// Print out the line received from the server.
		String msg = e.getMessage().toString();
		System.out.println("messageReceived---" + msg);
		if (msg.startsWith("id:")) {
			System.out.println("recive and will run thread");
			NodeClienTaskThread clientrun = new NodeClienTaskThread(e.getMessage().toString(), ctx);
			clientrun.start();
		} else {
			System.out.println("Others!!!");
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		System.out.println("exceptionCaught Exception ~~~~~~~~" + e.getCause());
		logger.log(Level.WARNING, "Unexpected exception from downstream.", e.getCause());
		if (e.getChannel().isOpen()) {
			e.getChannel().close();
		}
		NodeClient.getInstance().status = false;
	}
}