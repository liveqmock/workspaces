package com.iwgame.xnetty.agentNode;

import static org.jboss.netty.channel.Channels.pipeline;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import com.iwgame.xnetty.model.ConncetModel;

/**
 * 创建一个netty client端
 */
public class AgentNode {

	private static AgentNode agentNode = new AgentNode();

	private AgentNode() {

	}

	public static AgentNode getInstance() {
		return agentNode;
	}

	public Channel channel;

	private int tryCount = 0;

	private boolean tryStats = true;

	public void setTryStats(boolean tryStats) {
		this.tryStats = tryStats;
	}

	public void run() {
		ClientBootstrap boostrap = new ClientBootstrap();
		boostrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		// Configure the pipeline factory.
		boostrap.setPipelineFactory(new ChannelPipelineFactory() {

			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = pipeline();
				pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()));
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				
				
//				pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
//				pipeline.addLast("encoder", new ObjectEncoder());
				// DelimiterBasedFrameDecoder 保证数据完整性
				// 数据在传输过程中，产生数据包碎片（TCP/IP数据传输时大数据包无法一次传输，被拆分成小数据包，小数据包即为数据包碎片），这就造成了实际接收的数据包和发送的数据包不一致的情况
//				pipeline.addLast("framer", new DelimiterBasedFrameDecoder(1, Delimiters.lineDelimiter()));
				pipeline.addLast("handler", new AgentNodeHandler());
				return pipeline;
			}

		});

		// 长连接
		boostrap.setOption("child.tcpNoDelay", true);
		boostrap.setOption("child.keepAlive", true);
		ChannelFuture future = boostrap.connect(new InetSocketAddress("127.0.0.1", 9090));
		System.out.println("开始连接CenterMgr......");
		this.channel = future.awaitUninterruptibly().getChannel();

		if (!future.isSuccess()) {
			tryCount++;
			boostrap.releaseExternalResources();
			System.err.println("连接失败......");
			while (tryStats) {

				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
				}
				System.out.println("尝试连接" + tryCount + "次");
				run();
			}

		} else {
			tryStats = false;
			System.out.print("连接CenterMgr成功......");
			ConncetModel model = new ConncetModel();
			model.setUid(model.getSystemInfo().getMacAddress());
			channel.write(model.toString()+ " \r\n");
		}
	}

	public Channel getChannel() {
		return channel;
	}

	public static void main(String[] args) {
		AgentNode agentNode = new AgentNode();
		agentNode.run();
	}
}
