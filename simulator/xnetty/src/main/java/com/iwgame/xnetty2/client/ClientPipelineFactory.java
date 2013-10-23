package com.iwgame.xnetty2.client;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class ClientPipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {

		ChannelPipeline pipeline = Channels.pipeline();

		pipeline.addLast("encode", new StringEncoder());

		pipeline.addLast("decode", new StringDecoder());

		pipeline.addLast("handler", new ClientHandler());

		return pipeline;

	}

}