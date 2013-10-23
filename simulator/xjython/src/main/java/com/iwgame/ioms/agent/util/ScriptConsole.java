package com.iwgame.ioms.agent.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ScriptConsole {

	public static void main(String[] args) throws Throwable {
		
		String params = "a b c";
		Process proc = Runtime.getRuntime().exec("python /Users/jjwu/Desktop/fibo.py "+params);
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream());
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream());
		errorGobbler.start();
		outputGobbler.start();
		proc.waitFor();
	}
}

class StreamGobbler extends Thread {
	InputStream is;

	public StreamGobbler(InputStream is) {
		this.is = is;
	}

	@Override
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
