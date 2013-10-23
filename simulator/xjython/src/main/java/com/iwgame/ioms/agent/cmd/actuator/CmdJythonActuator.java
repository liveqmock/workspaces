package com.iwgame.ioms.agent.cmd.actuator;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import com.iwgame.ioms.agent.model.Command;

public class CmdJythonActuator implements CmdActuator {

	private final String basePath = "/Users/jjwu/Desktop/";

	private Command command;

	public CmdJythonActuator() {

	}

	public CmdJythonActuator(Command command) {
		this.command = command;
	}

	@Override
	public String exec() {
		if (command != null) {
			// 设置调用时间
			PythonInterpreter interp = new PythonInterpreter();
			String filepath = basePath+command.getCommandid().replace(".", "/")+".py";
			String script = command.getCommandid().substring(command.getCommandid().lastIndexOf(".")+1);
			interp.execfile(filepath);
			PyFunction func = interp.get(script+"_func",PyFunction.class);
			PyObject pyobj =  null;
			if(command.getParams()!=null){
				 pyobj = func.__call__(new PyString(command.getParams()));
			}else{
				 pyobj = func.__call__();
			}
			// 设置返回时间
			return pyobj.asString();
		}
		return null;
	}

}
