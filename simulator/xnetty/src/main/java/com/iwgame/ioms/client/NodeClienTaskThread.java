package com.iwgame.ioms.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.jboss.netty.channel.ChannelHandlerContext;

import com.iwgame.ioms.common.TaskCmd;

public class NodeClienTaskThread extends Thread {
	//命令
	public String cmd;
	public String id;
	//任务载体
	public TaskCmd task;
	 
	//通道
	public ChannelHandlerContext ctx;
	public ChannelHandlerContext getCtx() {
		return ctx;
	}
	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getCmd() {
		return cmd;
	}
	public TaskCmd getTask() {
		return task;
	}
	public void setTask(TaskCmd task) {
		this.task = task;
	}
	public NodeClienTaskThread(String cmd,ChannelHandlerContext ctx){
		this.cmd=cmd;
		getTaskByCmd(cmd);
		this.ctx=ctx;
	}
	public void getTaskByCmd(String cmd){
		this.cmd=cmd.substring(cmd.indexOf("cmd"));
		this.id=cmd.substring(3,cmd.indexOf("cmd"));
	}
	@Override
	public void run(){
		if(cmd.indexOf("ping")>-1){
			try {
				Runtime r = Runtime.getRuntime();
				Process p = r.exec(cmd);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO8859_1"));
				String line = br.readLine();
				int i = 0;
				while ((line != null) && (i < 10)) {
					line=new String(line.getBytes("ISO8859_1"), "GBK");
					System.out.println("renturn i="+i+"  line is "+line);
					if(!line.equals("")){
						i++;
						ctx.getChannel().write("id:"+id+"i="+i+ " "+ line+"\r\n");
					}
					line = br.readLine();
					
				}
				br.close();
			
				p.destroy();
				
				System.out.println("thread id ="+id+" is end");
			} catch (Exception e) {
			}
		}
	}
}
