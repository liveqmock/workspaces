package com.iwgame.ioms.agent.cmd;

/**
 * 命令执行器异常
 *
 * @author jjwu
 */
public class NotFindActuatorException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -388417618106319772L;

	public NotFindActuatorException() {
		super();
	}

	public NotFindActuatorException(String message, Throwable e) {
		super(message, e);
	}

	public NotFindActuatorException(String message) {
		super(message);
	}

	public NotFindActuatorException(Throwable e) {
		super(e);
	}
}
