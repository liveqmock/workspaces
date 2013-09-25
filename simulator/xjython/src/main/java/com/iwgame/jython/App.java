package com.iwgame.jython;


import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {

		PythonInterpreter interp = new PythonInterpreter();
		interp.exec("import sys");
		interp.exec("sys.path.append('/Users/jjwu/Desktop')");
		
		//test
//		interp.exec("import test");
//		interp.exec("a = test.helloworld()");
//		PyObject object = interp.get("a");
//		System.out.println(object.toString());
		

//		// 1.
//		// 引入系统模块，并将[./pythonsrc]加入模块搜索路径中去。
//		interp.exec("import sys");
//		interp.exec("sys.path.append('/Users/jjwu/Desktop')");
//
//		// 2. 引入 fibo, 执行fibo.fib(100), ok
//		interp.exec("import fibo");
//		interp.exec("fibo.fib(100)");
//
		// 3. 在Java 程序中获得 python内置类型的变量值。
//		String text = "'this is a bad day'";
//		interp.exec("x = " + text + " + '!!!'");
//		interp.exec("print x");
//		PyObject object = interp.get("x");
//		PyString xString = object.__str__();
//		String outX = xString.asString();
//
//		4.流的形式
//		interp.exec("_file = open('/Users/jjwu/Desktop/fibo.py')");
//		object = interp.get("_file");
//		PyFile file = (PyFile) object;
//		PyString content = file.read(100);
//		System.out.println(content);

//		interp.exec("array = _file.read(100)");
//		String array = interp.get("array").asString();
//		System.out.println(array);
//
//		interp.exec("_file.close()");
//		file.close();

	}
}
