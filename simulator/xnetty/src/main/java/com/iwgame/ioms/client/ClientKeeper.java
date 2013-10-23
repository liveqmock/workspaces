package com.iwgame.ioms.client;

public class ClientKeeper extends Thread{
	@Override
	public void run(){
		while(true){
			try{
				boolean ff= NodeClient.status;
				System.out.println("keeper is run~~"+ff);
				if(!ff){
					NodeClient.init(3);
				}else{
					NodeClient.keepLive();
				}
				Thread.sleep(10*1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}

}
