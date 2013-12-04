package com.iwgame.ioms.agent.util;

import java.io.IOException;

public class CommandExecutor {
	private final String commandInformation;

	public CommandExecutor(String commandInformation) {
		this.commandInformation = commandInformation;
	}

	public int executeCommand() throws IOException, InterruptedException {
		int exitValue = -99;
		try {
			Process proc = Runtime.getRuntime().exec(commandInformation);
			ThreadedStreamHandler streamHandler = new ThreadedStreamHandler(proc.getInputStream());
			streamHandler.start();
			return proc.waitFor();
		} catch (IOException e) {
			return exitValue;
		} catch (InterruptedException e) {
			return exitValue;
		}
	}
}
