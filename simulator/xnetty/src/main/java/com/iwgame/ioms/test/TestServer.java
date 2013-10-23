package com.iwgame.ioms.test;

import com.iwgame.ioms.centermgr.CenterMgrServer;

public class TestServer {
	public static void main(String[] args){
		
		TestSendThread tt=new TestSendThread(10);
		tt.start();
		
		CenterMgrServer cs=new CenterMgrServer();
		cs.main(null);
		
	}
}
