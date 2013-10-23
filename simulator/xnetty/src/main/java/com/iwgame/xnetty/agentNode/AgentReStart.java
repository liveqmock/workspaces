package com.iwgame.xnetty.agentNode;

public class AgentReStart implements Runnable{

	@Override
	public void run() {
		AgentNode.getInstance().setTryStats(true);
		AgentNode.getInstance().run();
	}

}
