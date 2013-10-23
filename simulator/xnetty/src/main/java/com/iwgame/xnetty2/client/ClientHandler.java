package com.iwgame.xnetty2.client;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ClientHandler extends SimpleChannelHandler {

	final static Logger log = Logger.getLogger(ClientHandler.class);

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)

	throws Exception {

		// 异常处理

		log.error(e.getCause());

		e.getChannel().close();

	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)

	throws Exception {

		// 客户端消息的接收

		// ChannelBuffer buf = (ChannelBuffer) e.getMessage();

		// byte[] b = new byte[200];

		// int i=0;

		// while(buf.readable()){

		// b[i++]=buf.readByte();

		// }

		// byte[] dest = new byte[i];

		// System.arraycopy(b, 0, dest,0, i);

		// message = new Message(dest);

		// System.out.println("获取服务器端下发的消息："+message.getValue());

		Object message = e.getMessage();

		if (message instanceof String) {

			log.info("获取服务器端下发的消息：" + message);

		}

	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

		log.info("。。通道已连接。。");

	}

}
