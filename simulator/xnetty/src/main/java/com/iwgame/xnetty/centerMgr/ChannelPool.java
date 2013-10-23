package com.iwgame.xnetty.centerMgr;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.jboss.netty.channel.ChannelHandlerContext;

/**
 * 通道缓存
 */
public class ChannelPool {
	
	private final static ChannelPool instance = new ChannelPool();
	
	private final static Hashtable<String, ChannelHandlerContext> channelpool = new Hashtable<String, ChannelHandlerContext>();
	
	private ChannelPool() {
		
	}
	
	public static ChannelPool getInstance(){
		return instance;
	}
	
	public synchronized void add(String key, ChannelHandlerContext ctx) {
		System.out.println("pool add key is " + key);
		channelpool.put(key, ctx);
	}

	public Hashtable<String, ChannelHandlerContext> getChannelPool() {
		return channelpool;
	}

	public synchronized void remove(ChannelHandlerContext ctx) {
		Enumeration<ChannelHandlerContext> el = channelpool.elements();
		Iterator<String> il = channelpool.keySet().iterator();
		while (el.hasMoreElements()) {
			ChannelHandlerContext p = (ChannelHandlerContext) el.nextElement();
			String key = (String) il.next();
			if (p.equals(ctx)) {
				channelpool.remove(key);
			}
		}
	}
	
	public synchronized void remove(String key) {
		if(channelpool.contains(key)){
			channelpool.remove(key);
			System.out.println("remove key ====" + key);
		}
	}

}
