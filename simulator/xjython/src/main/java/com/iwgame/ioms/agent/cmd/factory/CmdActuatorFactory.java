package com.iwgame.ioms.agent.cmd.factory;

import com.iwgame.ioms.agent.cmd.NotFindActuatorException;
import com.iwgame.ioms.agent.cmd.actuator.CmdActuator;
import com.iwgame.ioms.agent.cmd.actuator.CmdJythonActuator;
import com.iwgame.ioms.agent.cmd.actuator.CmdPythonActuator;
import com.iwgame.ioms.agent.cmd.actuator.CmdShellActuator;
import com.iwgame.ioms.agent.model.Command;

/**
 * 命令执行器工厂,简单工厂
 * @author jjwu
 *
 */
public abstract class CmdActuatorFactory {


	public static CmdActuator createCmdActuator(Command command) throws NotFindActuatorException{
		if(command.getCommandid().startsWith("shell")){
			return new CmdShellActuator(command);
		}else if(command.getCommandid().startsWith("jython")){
			return new CmdJythonActuator(command);
		}else if(command.getCommandid().startsWith("python")){
			return new CmdPythonActuator(command);
		}else{
			throw new NotFindActuatorException();
		}
	}

}
