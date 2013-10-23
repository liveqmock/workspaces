package com.iwgame.ioms.agent.cmd.actuator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.iwgame.ioms.agent.model.Command;

public class CmdShellActuator  implements CmdActuator {

	private Command command;

	public CmdShellActuator(){

	}

	public CmdShellActuator(Command command){
		this.command = command;
	}

	@Override
	public String exec() {
		try {
			String cmd = "python /Users/jjwu/Desktop/fibo.py "+ command.getParams();
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO8859_1"));
			String line = br.readLine();
			while ((line != null)) {
				line = new String(line.getBytes("ISO8859_1"), "GBK");
				if (!line.equals("")) {
					System.out.println("message:" + line);
				}
				line = br.readLine();
			}
			br.close();
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
