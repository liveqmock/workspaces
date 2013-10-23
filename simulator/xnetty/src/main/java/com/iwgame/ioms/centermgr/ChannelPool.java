package com.iwgame.ioms.centermgr;

import java.util.Hashtable;

import org.jboss.netty.channel.ChannelHandlerContext;

public class ChannelPool {
	
	private static ChannelPool instance = new ChannelPool();
	
	private static Hashtable pool = new Hashtable();

	private ChannelPool() {
	}

	public static ChannelPool getInstance() {
		return instance;
	}

	public synchronized void add(String key, ChannelHandlerContext ctx) {
		System.out.println("pool add key is " + key);
		pool.put(key, ctx);
	}

	public Hashtable getPool() {
		return pool;
	}

	public synchronized void remove(ChannelHandlerContext ctx) {
		java.util.Enumeration el = pool.elements();
		java.util.Iterator il = pool.keySet().iterator();
		while (el.hasMoreElements()) {
			ChannelHandlerContext p = (ChannelHandlerContext) el.nextElement();
			String key = (String) il.next();
			if (p.equals(ctx)) {
				pool.remove(key);
				System.out.println("remove key ====" + key);
			}

		}
	}

}
