package com.iwgame.jython.chapter01;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JavaExcePython {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PythonInterpreter interp = new PythonInterpreter();
		//导入系统类库
		interp.exec("import sys");
		//导入本地的python文件目录
		interp.exec("sys.path.append('/Users/jjwu/Desktop')");
		
		
		//调用方式1:
		//导入python文件
		interp.exec("import fibo");
		//执行python文件里面的函数
		interp.exec("fibo.fib(100)");
		
		//执行python函数获取返回值
		interp.exec("import test");
		interp.exec("a = test.helloworld()");
		PyObject object = interp.get("a");
		System.err.println(object.asString());
		

	}

}
