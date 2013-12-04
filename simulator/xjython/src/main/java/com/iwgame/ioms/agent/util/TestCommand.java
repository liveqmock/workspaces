package com.iwgame.ioms.agent.util;


public class TestCommand {

	public static void main(String[] args) throws Throwable {

		String commandInformation = "python /Users/jjwu/Documents/workspaces/simulator/xjython/src/main/java/com/iwgame/ioms/agent/util/test.py aaaa";

		CommandExecutor commandExecutor = new CommandExecutor(commandInformation);
		System.out.println(commandExecutor.executeCommand());
	}
}

