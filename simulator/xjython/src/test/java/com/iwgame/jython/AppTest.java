package com.iwgame.jython;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import com.iwgame.ioms.agent.cmd.actuator.CmdActuator;
import com.iwgame.ioms.agent.cmd.factory.CmdActuatorFactory;
import com.iwgame.ioms.agent.model.Command;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void test() throws Exception {
		Command command = new Command();
		command.setCommandid("jython.test");
		command.setName("测试");
		command.setParams("参数");
		CmdActuator actuator = CmdActuatorFactory.createCmdActuator(command);
		System.out.println(actuator.exec());
	}

	/**
	 * 执行 jython 函数
	 */
	@Test
	public void test2() {
		PythonInterpreter interp = new PythonInterpreter();
		interp.execfile("/Users/jjwu/Desktop/jpython/test.py");
		PyFunction func = interp.get("test_func", PyFunction.class);

		// int a = 100;
		PyObject pyobj = func.__call__();
		System.out.println("anwser = " + pyobj.toString());
	}

	/**
	 * 执行 脚本
	 */
	@Test
	public void test3() {
		try {
			String cmd = "ping -c3 www.baidu.com";
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(cmd);

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO8859_1"));

			String messageline = null;
			boolean bl = false;

			while (!bl) {
				try {
					int i = p.exitValue();
					System.out.println(i);
					bl = true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					while ((messageline = br.readLine()) != null) {
						System.out.println(messageline + "/****** timestamp: " + System.currentTimeMillis() + "*******/");
					}
				}
			}

			br.close();
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4() {
		try {
			String cmd = "python /Users/jjwu/Desktop/fibo.py ";
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(cmd);
//			BufferedReader br = new BufferedReader();
			int i = p.waitFor();
			System.out.println(i);
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
	}
}
