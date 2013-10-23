package com.iwgame.ioms.client;

public class ClientMain {
	public static void main(String[] args){

		NodeClient.init(3);
		ClientKeeper keeper=new ClientKeeper();
		keeper.start();
		while(true){
			try{
				Thread.sleep(100*1000);
			}catch(Exception e){

			}
		}
	}
}
