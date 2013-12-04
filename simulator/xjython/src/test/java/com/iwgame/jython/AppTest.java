package com.iwgame.jython;


import org.junit.Test;
import org.python.core.PyFunction;
import org.python.util.PythonInterpreter;


/**
 * Unit test for simple App.
 */
public class AppTest {

	/**
	 * 执行 jython 函数
	 */
	@Test
	public void test() {
		
		PythonInterpreter interp = new PythonInterpreter();
		
		interp.execfile("/Users/jjwu/Documents/workspaces/simulator/xjython/src/test/java/com/iwgame/jython/fibo.py");
		PyFunction func = (PyFunction)interp.get("main",PyFunction.class);
		func.__call__();
	}
}
