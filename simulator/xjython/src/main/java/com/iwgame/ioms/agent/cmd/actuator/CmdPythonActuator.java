package com.iwgame.ioms.agent.cmd.actuator;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

import com.iwgame.ioms.agent.model.Command;

public class CmdPythonActuator  implements CmdActuator {


	private Command command;

	public CmdPythonActuator(){

	}

	public CmdPythonActuator(Command command){
		this.command = command;
	}

	@Override
	public String exec() {
		try {
			String cmd = "python /Users/jjwu/Desktop/fibo.py "+command.getParams();
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(cmd);
//			BufferedReader br = new BufferedReader();
			LineNumberReader input = new LineNumberReader (new InputStreamReader(p.getInputStream(), "ISO8859_1"));
			String line = input.readLine();
			while ((line != null)) {
				line = new String(line.getBytes("ISO8859_1"), "GBK");
				if (!line.equals("")) {
					System.out.println("message:" + line);
				}
				line = input.readLine();
			}
			input.close();
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
