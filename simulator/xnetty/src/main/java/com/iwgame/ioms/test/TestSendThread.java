package com.iwgame.ioms.test;

import java.util.Hashtable;

import org.jboss.netty.channel.ChannelHandlerContext;

import com.iwgame.ioms.centermgr.ChannelPool;

public class TestSendThread extends Thread {
	public TestSendThread(){
		
	}
	private int time;
	public TestSendThread(int time){
		this.time=time;
	}
	public void run1(){
		while(true){
			Hashtable ll=ChannelPool.getInstance().getPool();

				ChannelHandlerContext ctx=(ChannelHandlerContext)ll.get("client1");
				if(ctx!=null){
					ctx.getChannel().write("send msg from client 1 by  thread==="+time+".\r\n");
				}
				
				ChannelHandlerContext ctx1=(ChannelHandlerContext)ll.get("client2");
				if(ctx1!=null){
					ctx1.getChannel().write("send msg from client 2 by thread==="+time+".\r\n");
				}
							
			try{
				Thread.sleep(time*1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
		
		
		public void run(){
			while(true){
				System.out.println("test send thread is run~~~");
				Hashtable ll=ChannelPool.getInstance().getPool();

					ChannelHandlerContext ctx=(ChannelHandlerContext)ll.get("client1");
				if(ctx!=null){
						String id=Math.round(Math.random()*8999+1000)+"";
						System.out.println("create id is "+id);
						ctx.getChannel().write("id:"+id+" cmd cmd /c ping 127.0.0.1 -t \r\n");
					}
													
				try{
					Thread.sleep(time*1000);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		
	}
}
