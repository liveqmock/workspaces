package com.iwgame.ioms.agent.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ThreadedStreamHandler extends Thread {
	private final InputStream inputStream;

	/**
	 *
	 * @param inputStream
	 */
	ThreadedStreamHandler(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	@Override
	public void run() {

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				// TODO handle notice message
				System.out.println(line);

			}
		} catch (IOException ioe) {
			// TODO handle this better
			ioe.printStackTrace();
		} catch (Throwable t) {
			// TODO handle this better
			t.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// ignore this one
			}
		}
	}
}
