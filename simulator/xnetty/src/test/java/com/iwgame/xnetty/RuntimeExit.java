package com.iwgame.xnetty;

import java.util.Scanner;

import org.jboss.netty.channel.Channel;

import com.iwgame.xnetty.agentNode.AgentNode;



public class RuntimeExit {
	public static void main(String[] args) throws Throwable{
//		RunThread aa=new RunThread();
//		aa.start();
//		while(true){
//			try {
//				Thread.sleep(10*1000);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//
//			System.out.println("running");
//		}

//		Console console = System.console();
//
//		if(console != null){
//			System.out.println(console.readLine());
//		}else{
//			System.out.println("不支持控制台!");
//		}
		AgentNode.getInstance().run();

		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println(scanner.nextLine());
			Channel chanenl =  AgentNode.getInstance().getChannel();
			chanenl.write("");
			Thread.sleep(100);
		}




	}
}

