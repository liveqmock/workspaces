package com.iwgame.xnetty.agentNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

import com.iwgame.xnetty.model.ConncetModel;

public class ScriptConsole {

	public static void exec(Channel channel) throws Throwable {
		
		Process proc = Runtime.getRuntime().exec("python /Users/jjwu/Desktop/fibo.py");
//		StreamGobbler errorGobbler = new StreamGobbler(channel, proc.getErrorStream());
		StreamGobbler outputGobbler = new StreamGobbler(channel, proc.getInputStream());
//		errorGobbler.start();
		outputGobbler.start();
//		proc.waitFor();
//		channel.write("client message ..... \r\n");
		
	}

}

class StreamGobbler extends Thread {
	InputStream is;
	Channel channel;

	public StreamGobbler(Channel channel, InputStream is) {
		this.is = is;
		this.channel = channel;
	}

	@Override
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.err.println("client_"+line);
				channel.write(line+"\r\n");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}

